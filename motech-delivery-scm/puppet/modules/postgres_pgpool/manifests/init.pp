class postgres_pgpool ( $postgresUser, $postgresPassword, $postgresMachine, $postgresMaster, $postgresSlave) {

    $allPacks = [ "postgresql91", "postgresql91-server", "postgresql91-libs", "postgresql91-contrib", "postgresql91-devel"]

    file{"/tmp/postgres-repo.rpm":
        source => "puppet:///modules/postgres/pgdg-$os-9.1-4-$word.noarch.rpm",
    }

    exec { "run_postgres_repo":
        require => File["/tmp/postgres-repo.rpm"],
        command => "rpm -i /tmp/postgres-repo.rpm",
        creates => "/etc/yum.repos.d/pgdg-91-centos.repo"
    }

    package { "postgres_packs":
        name => $allPacks,
		ensure => "present",
		require => Exec["run_postgres_repo"],
	}

	user { "${postgresUser}":
        ensure     => present,
        shell      => "/bin/bash",
        home       => "/home/$postgresUser",
        password   => $postgresPassword,
        require    => Exec["run_postgres_repo"],
    }

    file { "/usr/local/pgsql/":
       ensure =>  "directory",
       owner => "${postgresUser}",
    }

    file { "/usr/local/pgsql/data":
        require => File["/usr/local/pgsql/"],
        ensure =>  "directory",
        owner => "${postgresUser}",
    }

    exec { "initdb":
        command =>"/usr/pgsql-9.1/bin/initdb -D /usr/local/pgsql/data",
        user => "${postgresUser}",
        unless => "/usr/bin/test -f /usr/local/pgsql/data/pg_ident.conf",
        #onlyif => "grep -c post /usr/local/pgsql/data/",
        require => [File["/usr/local/pgsql/data"], Package["postgres_packs"]],
    }

    file { "/etc/init.d/postgresql":
       ensure => "link",
       target => "/etc/init.d/postgresql-9.1",
    }

    exec { "start-server":
          command =>"/usr/pgsql-9.1/bin/postgres -D /usr/local/pgsql/data &",
          user => "${postgresUser}",
          require => Exec["initdb"],
    }

    exec{"backup_conf":
            cwd     => "/usr/local/pgsql/data/",
            command => "mv postgresql.conf postgresql.conf.backup && mv pg_hba.conf pg_hba.conf.backup",
            user    => "${postgresUser}",
            require => Exec["start-server"],
        }

    file {"/usr/local/pgsql/data/postgresql.conf":
            content => template("postgres_pgpool/postgresql.conf.erb"),
            owner => "${postgresUser}",
            group => "${postgresUser}",
            mode   =>  600,
            require => Exec["backup_conf"],
         }

    case $postgresMachine {

        master:{
            file {"/usr/local/pgsql/data/pg_hba.conf":
                content => template("postgres_pgpool/master_pg_hba.erb"),
                owner => "${postgresUser}",
                group => "${postgresUser}",
                mode   =>  600,
                require => File["/usr/local/pgsql/data/postgresql.conf"],
            }
        }

        slave:{
            file {"/usr/local/pgsql/data/pg_hba.conf":
                content => template("postgres_pgpool/slave_pg_hba.conf.erb"),
                owner => "${postgresUser}",
                group => "${postgresUser}",
                mode   =>  600,
                require => File["/usr/local/pgsql/data/postgresql.conf"],
            }
        }
    }

    package { "postgresql_libs":
        name => postgresql-libs,
		ensure => "present",
		require => File["/usr/local/pgsql/data/pg_hba.conf"],
	}

    package {"pgpool":
        name => "pgpool-II-91.x86_64",
        ensure => "present",
        require => Package["postgresql_libs"],
    }

    file { "/usr/local/etc/pgpool.conf":
            content => template("postgres_pgpool/pgpool.conf.erb"),
            owner => "root",
            group => "root",
            mode => 644,
            require => Package["pgpool"],
         }

    file { "/usr/local/etc/pcp.conf":
            content => template("postgres_pgpool/pcp.conf.erb"),
            owner => "root",
            group => "root",
            mode => 644,
            require => File["/usr/local/etc/pgpool.conf"],
         }

    file { "/usr/local/pgsql/data/basebackup.sh":
            content => template("postgres_pgpool/basebackup.sh.erb"),
            owner => "root",
            group => "root",
            mode => 755,
            require => File["/usr/local/etc/pcp.conf"],
         }

    file { "/usr/local/pgsql/data/pgpool_remote_start.sh":
            content => template("postgres_pgpool/pgpool_remote_start.sh.erb"),
            owner => "root",
            group => "root",
            mode => 755,
            require => File["/usr/local/pgsql/data/basebackup.sh"],
         }

    file { "/usr/local/etc/failover.sh":
            content => template("postgres_pgpool/failover.sh.erb"),
            owner => "root",
            group => "root",
            mode => 755,
            require => File["/usr/local/pgsql/data/pgpool_remote_start.sh"],
        }

    exec { "pgpool_start":
        command => "pgpool -n -d > /tmp/pgpool.log 2>&1 &",
        require => File["/usr/local/etc/failover.sh"]
        }
}

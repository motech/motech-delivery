class postgres ( $postgresUser, $postgresPassword, $postgresMachine, $postgresMaster, $postgresSlave) {

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
        require => [File["/usr/local/pgsql/data"], Package["postgres_packs"]],
    }

    exec { "start-server":
           command =>"/usr/pgsql-9.1/bin/postgres -D /usr/local/pgsql/data &",
           user => "${postgresUser}",
           require => Exec["initdb"],
    }

    file { "/etc/init.d/postgresql":
        ensure => "link",
        target => "/etc/init.d/postgresql-9.1",
    }

    case $postgresMachine {

        master:{
            exec{"backup_master_conf":
                    cwd     => "/usr/local/pgsql/data/",
                    command => "mv postgresql.conf postgresql.conf.backup && mv pg_hba.conf pg_hba.conf.backup",
                    user    => "${postgresUser}",
                    require => Exec["start-server"],
            }

            file {"/usr/local/pgsql/data/postgresql.conf":
                    content => template("postgres/master_postgresql.erb"),
                    owner => "${postgresUser}",
                    group => "${postgresUser}",
                    mode   =>  600,
                    require => Exec["backup_master_conf"],
            }

            file {"/usr/local/pgsql/data/pg_hba.conf":
                content => template("postgres/master_pg_hba.erb"),
                owner => "${postgresUser}",
                group => "${postgresUser}",
                mode   =>  600,
                require => Exec["backup_master_conf"]
            }
        }

        slave:{
            exec{"backup_slave_conf":
                cwd     => "/usr/local/pgsql/data/",
                command => "mv postgresql.conf postgresql.conf.backup",
                user    => "${postgresUser}",
                require => Exec["start-server"],
            }

            file {"/usr/local/pgsql/data/postgresql.conf":
                content => template("postgres/slave_postgresql.erb"),
                owner => "${postgresUser}",
                group => "${postgresUser}",
                mode   =>  600,
                require => Exec["backup_slave_conf"],
            }

            file {"/usr/local/pgsql/data/recovery.conf":
                content => template("postgres/slave_recovery.erb"),
                owner => "${postgresUser}",
                group => "${postgresUser}",
                mode   =>  600,
            }
        }
    }

}
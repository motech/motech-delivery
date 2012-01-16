class postgres {

    $allPacks = [ "postgresql91", "postgresql91-server", "postgresql91-libs", "postgresql91-contrib", "postgresql91-devel"]

    file{ "/tmp/postgres-repo.rpm":
        source => "puppet:///modules/postgres/pgdg-centos6-9.1-4-64b.noarch.rpm",
    }

    exec { "run_postgres_repo":
        require => File["/tmp/postgres-repo.rpm"],
        command => "rpm -i /tmp/postgres-repo.rpm",
    }

    package { $allPacks:
		ensure => "present",
	}

	user { "${postgresUser}":
        ensure     => present,
        shell      => "/bin/bash",
        home       => "/home/$postgresUser",
        password   => $postgresPassword,
    }

    file { "/usr/local/pgsql/":
        ensure =>  "directory",
        owner => $postgresUser,
    }

    file { "/usr/local/pgsql/data":
        require => File["/usr/local/pgsql/"],
        ensure =>  "directory",
        owner => $postgresUser,
    }

    exec { "initdb":
        command =>"/usr/pgsql-9.1/bin/initdb -D /usr/local/pgsql/data",
        require => File["/usr/pgsql-9.1/bin/initdb"],
    }

}
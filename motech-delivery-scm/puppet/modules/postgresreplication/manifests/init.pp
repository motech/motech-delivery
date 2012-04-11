class postgresreplication ( $postgresUser, $postgresMachine, $postgresMaster, $postgresSlave) {

    case $postgresMachine {

        master:{
            file {"/usr/local/pgsql/data/postgresql.conf":
                    content => template("postgres/master_postgresql.erb"),
                    owner => "${postgresUser}",
                    group => "${postgresUser}",
                    mode   =>  600,
            }

            file {"/usr/local/pgsql/data/pg_hba.conf":
                content => template("postgres/master_pg_hba.erb"),
                owner => "${postgresUser}",
                group => "${postgresUser}",
                mode   =>  600,
            }
        }

        slave:{
           file {"/usr/local/pgsql/data/postgresql.conf":
                content => template("postgres/slave_postgresql.erb"),
                owner => "${postgresUser}",
                group => "${postgresUser}",
                mode   =>  600,
            }

            file {"/usr/local/pgsql/data/recovery.conf":
                content => template("postgres/slave_recovery.erb"),
                owner => "${postgresUser}",
                group => "${postgresUser}",
                mode   =>  600,
            }
        }
    }

    exec {"backup_master_conf":
        cwd     => "/usr/local/pgsql/data/",
        command => "mv postgresql.conf postgresql.conf.backup && mv pg_hba.conf pg_hba.conf.backup",
        user    => "${postgresUser}",
    }

    exec { "restart-server":
           command =>"service postgresql restart &",
    }
}
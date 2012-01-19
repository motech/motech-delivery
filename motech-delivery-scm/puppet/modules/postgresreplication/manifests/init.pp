
class postgresreplication ( $postgresMaster, $postgresSlave, $postgresUser, $postgresMachine) {

    case $postgresMachine{
        master:{
            exec{"backup_master_conf":
                    cwd     => "/usr/local/pgsql/data/",
                    command => "mv postgresql.conf postgresql.conf.backup && mv pg_hba.conf pg_hba.conf.backup",
                    user    => "${postgresUser}",
                }

            file {"/usr/local/pgsql/data/postgresql.conf":
                    content => template("postgresreplication/master_postgresql.erb"),
                    owner => "${postgresUser}",
                    group => "${postgresUser}",
                    mode   =>  600,
                    require => Exec["backup_master_conf"],
                }

            file {"/usr/local/pgsql/data/pg_hba.conf":
                content => template("postgresreplication/master_pg_hba.erb"),
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
            }

            file {"/usr/local/pgsql/data/postgresql.conf":
                content => template("postgresreplication/slave_postgresql.erb"),
                owner => "${postgresUser}",
                group => "${postgresUser}",
                mode   =>  600,
                require => Exec["backup_slave_conf"]
            }

            file {"/usr/local/pgsql/data/recovery.conf":
                content => template("postgresreplication/slave_recovery.erb"),
                owner => "${postgresUser}",
                group => "${postgresUser}",
                mode   =>  600,
            }
        }
    }
}
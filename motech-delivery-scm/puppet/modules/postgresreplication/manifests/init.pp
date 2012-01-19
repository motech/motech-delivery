
class postgresreplication ( $postgresMaster, $postgresSlave, $postgresUser) {

    file {"create_master_postgresql_conf":
        path => "/usr/local/pgsql/data/postgresql.conf",
        content => template("postgresreplication/master_postgresql.erb"),
        owner => "${postgresUser}",
    }

    file {"create_master_pg_hba_conf":
        path => "/usr/local/pgsql/data/pg_hba.conf",
        content => template("postgresreplication/master_pg_hba.erb"),
        owner => "${postgresUser}",
    }

    file {"create_slave_postgresql_conf":
        path => "/usr/local/pgsql/data/postgresql.conf",
        content => template("postgresreplication/slave_postgresql.erb"),
        owner => "${postgresUser}",
    }

    file {"create_slave_recovery_conf":
        path => "/usr/local/pgsql/data/recovery.conf",
        content => template("postgresreplication/slave_recovery.erb"),
        owner => "${postgresUser}",
    }
}
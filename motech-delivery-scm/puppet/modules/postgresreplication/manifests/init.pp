
class postgresreplication ( $postgresMaster, $postgresSlave ) {

    file {"create_master_postgresql_conf":
        path => "/usr/local/pgsql/data/postgresql.conf",
        content => template("postgresreplication/master_postgresql.erb"),
    }

    file {"create_master_pg_hba_conf":
        path => "/usr/local/pgsql/data/pg_hba.conf",
        content => template("postgresreplication/master_pg_hba.erb"),
    }

    file {"create_slave_postgresql_conf":
        path => "/usr/local/pgsql/data/postgresql.conf",
        content => template("postgresreplication/slave_postgresql.erb"),
    }

    file {"create_slave_recovery_conf":
        path => "/usr/local/pgsql/data/recovery.conf",
        content => template("postgresreplication/slave_recovery.erb"),
    }
}
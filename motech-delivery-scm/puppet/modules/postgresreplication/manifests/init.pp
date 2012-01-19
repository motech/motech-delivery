
class postgresreplication ( $postgresMaster, $postgresSlave, $postgresUser) {

    file {"/usr/local/pgsql/data/postgresql.conf":
        content => template("postgresreplication/master_postgresql.erb"),
        owner => "${postgresUser}",
        group => "${postgresUser}",
  	    mode   =>  600,
    }

    file {"/usr/local/pgsql/data/pg_hba.conf":
        content => template("postgresreplication/master_pg_hba.erb"),
        owner => "${postgresUser}",
        group => "${postgresUser}",
  	    mode   =>  600,
    }

    file {"/usr/local/pgsql/data/postgresql.conf":
        content => template("postgresreplication/slave_postgresql.erb"),
        owner => "${postgresUser}",
        group => "${postgresUser}",
  	    mode   =>  600,
    }

    file {"/usr/local/pgsql/data/recovery.conf":
        content => template("postgresreplication/slave_recovery.erb"),
        owner => "${postgresUser}",
        group => "${postgresUser}",
  	    mode   =>  600,
    }
}

class couchdbslave ( $masterServer, $dbNames ) {

  file {"/home/${motechUser}/couch-slave.sh" :
  	content => template("couchdbslave/couch-slave.sh"),
  	owner => "${motechUser}",
  	group => "${motechUser}",
  	mode   =>  764,
  }

  exec {"run_slave_script":
    require => File["/home/${motechUser}/couch-slave.sh"],
    command =>  "/home/${motechUser}/couch-slave.sh",
  }

}

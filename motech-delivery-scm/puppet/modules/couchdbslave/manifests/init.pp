
class couchdbslave ( $masterServer, $dbNames ) {

  file {"/home/${motechUser}/setup-slave.sh" :
  	content => template("couchdbslave/setup-slave.sh"),
  	owner => "${motechUser}",
  	group => "${motechUser}",
  	mode   =>  733,
  }

  exec {"/home/${motechUser}/setup-slave.sh":
    require => File["/home/${motechUser}/setup-slave.sh"],
    command =>  "/home/${motechUser}/setup-slave.sh",
  }

}

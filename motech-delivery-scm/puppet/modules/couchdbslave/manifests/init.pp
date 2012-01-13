class couchdbslave ($masterServer, $dbNames) {

  file {"/home/vijayrc/setup-slave.sh" :
    require => Service["couchdb"]
  	content => template("couchdb/setup-slave.sh"),
  	owner => "vijayrc",
  	group => "vijayrc",
  	mode   =>  777,
  }

}

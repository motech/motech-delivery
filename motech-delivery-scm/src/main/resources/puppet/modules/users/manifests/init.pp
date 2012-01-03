
class users ( $userName ) {
  user { "${userName}":
      ensure     => present,
      shell      => "/bin/bash",
      home       => "/home/${userName}",
  }
   
  file {"/etc/sudoers" :
  	content => template("users/sudoers.erb"),
  	owner => "root",
  	group => "root",
  	mode   =>  440,
  }  
}
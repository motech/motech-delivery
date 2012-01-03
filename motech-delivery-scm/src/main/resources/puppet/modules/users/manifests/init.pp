
class users ( $userName ) {
  user { "${userName}":
      ensure     => present,
      shell      => "/bin/bash",
      home       => "/home/${userName}",
  }
  
  exec { "$userName homedir":
    command => "/bin/cp -R /etc/skel /home/$userName; /bin/chown -R $userName:$userName /home/$userName",
    creates => "/home/$userName",
    require => User[$userName],
  }
   
  file {"/etc/sudoers" :
  	content => template("users/sudoers.erb"),
  	owner => "root",
  	group => "root",
  	mode   =>  440,
  }  
}
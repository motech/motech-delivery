
class users {
  user { '@motech_user':
      ensure     => present,
      shell      => '/bin/bash',
      home       => '/home/@motech_user',
  }
   
  file {"/etc/sudoers" :
  	source => "puppet:///modules/users/sudoers",
  	owner => "root",
  	group => "root",
  	mode   =>  440,
  }
}
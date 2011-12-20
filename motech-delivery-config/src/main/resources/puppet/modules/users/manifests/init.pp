
class users {
  user { 'tamasuper':
      ensure     => present,
      shell      => '/bin/bash',
      home       => '/home/tamasuper',
  }
   
  file {"/etc/sudoers" :
  	source => "puppet:///modules/users/sudoers",
  	owner => "root",
  	group => "root",
  	mode   =>  440,
  }
}
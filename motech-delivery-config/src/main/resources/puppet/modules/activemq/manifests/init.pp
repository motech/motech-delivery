class activemq {

  file { "/tmp/activemq.tar.gz":
    source => "puppet:///modules/activemq/apache-activemq-5.5.1-bin.tar.gz",
    require => Exec["sun_jdk_6"],
  }

  exec { "activemq_untar":
    command => "tar xfz /tmp/activemq.tar.gz",
    user => "tamasuper",
    cwd     => "/home/tamasuper",
    creates => "/home/tamasuper/apache-activemq-5.5.1",
    path    => ["/bin",],
  }
  
  
  file { "/etc/init.d/activemq" :
  	source => "puppet:///modules/activemq/activemq-init.d",
  	mode   =>  777,
  	group  => "root",
  	owner  => "root",
  	require => Exec["activemq_untar"], 
  }
  
  exec { "installservice" :
  	command => "/sbin/chkconfig --add activemq",
  	user => "root", 
  	require => File["/etc/init.d/activemq"],
  }


  service { "activemq":
    path       => "/home/tamasuper/apache-activemq-5.5.1/bin/activemq",
    ensure     => running,
    enable     => true,
    require => Exec["installservice"],
  }
}

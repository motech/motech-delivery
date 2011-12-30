class activemq {

  file { "/tmp/activemq.tar.gz":
    source => "puppet:///modules/activemq/apache-activemq-5.5.1-bin.tar.gz",
    require => Exec["sun_jdk_6"],
  }

  exec { "activemq_untar":
    command => "tar xfz /tmp/activemq.tar.gz",
    user => "@motech_user",
    cwd     => "/home/@motech_user",
    creates => "/home/@motech_user/apache-activemq-5.5.1",
    path    => ["/bin",],
  }

  file { "/tmp/activemq_init.d":
    source => "puppet:///modules/activemq/activemq-init.d", \
    require => Exec["activemq_untar"],
  }

  exec { "prepare_initd_script":
    command => "sed -i 's/MOTECH_USER_TO_BE_REPLACED/'@motech_user'/g' /tmp/activemq_init.d",
    require => Exec["/tmp/activemq_init.d"],
  }

  file { "/etc/init.d/activemq" :
  	source => "/tmp/activemq_init.d",
  	mode   =>  777,
  	group  => "root",
  	owner  => "root",
  	require => Exec["prepare_initd_script"],
  }
  
  exec { "installservice" :
  	command => "/sbin/chkconfig --add activemq",
  	user => "root", 
  	require => File["/etc/init.d/activemq"],
  }

  service { "activemq":
    path       => "/home/@motech_user/apache-activemq-5.5.1/bin/activemq",
    ensure     => running,
    enable     => true,
    require => Exec["installservice"],
  }
}

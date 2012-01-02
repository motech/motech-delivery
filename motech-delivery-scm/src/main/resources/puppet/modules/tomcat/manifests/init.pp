class tomcat {
  include users
  
  file { "/tmp/apache-tomcat-7.0.22.tar.gz":
    source => "puppet:///modules/tomcat/apache-tomcat-7.0.22.tar.gz",
    require => [User["@motech_user"],Exec["sun_jdk_6"]]
  }

  exec { "tomcat_untar":
    command => "tar xfz /tmp/apache-tomcat-7.0.22.tar.gz",
    user => "@motech_user",
    cwd     => "/home/@motech_user",
    creates => "/home/@motech_user/apache-tomcat-7.0.22",
    path    => ["/bin"],
    require => File["/tmp/apache-tomcat-7.0.22.tar.gz"],
  }

  file { "/tmp/tomcat_init.d":
    source => "puppet:///modules/tomcat/tomcat.initd", 
    require => Exec["tomcat_untar"],
  }

  exec { "prepare_initd_script":
    command => "sed -i 's/MOTECH_USER_TO_BE_REPLACED/'@motech_user'/g' /tmp/tomcat_init.d",
    require => Exec["/tmp/tomcat_init.d"],
  }

  file { "/etc/init.d/tomcat" :
  	source => "/tmp/tomcat_init.d",
  	mode   =>  777,
  	group  => "root",
  	owner  => "root",
  	require => Exec["prepare_initd_script"],
  }

  exec { "installtomcatservice" :
  	command => "/sbin/chkconfig --add tomcat",
  	user => "root", 
  	require => File["/etc/init.d/tomcat"],
  }
  
  service { "tomcat":
  	ensure => running,
  	enable => true,
  	hasstatus => false,
  	require => Exec["installtomcatservice"],
  }
}

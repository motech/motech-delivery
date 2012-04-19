class tomcat {
  include users
  
  exec {"gettomcattarfile" :
  	command => "/usr/bin/wget -O /tmp/apache-tomcat-7.0.22.tar.gz http://motechrepo.github.com/pub/motech/other/apache-tomcat-7.0.22.tar.gz",
  	require => [Exec["${motechUser} homedir"]]
  }
  
  exec { "tomcat_untar":
    command => "tar xfz /tmp/apache-tomcat-7.0.22.tar.gz",
    user => "${motechUser}",
    cwd     => "/home/${motechUser}",
    creates => "/home/${motechUser}/apache-tomcat-7.0.22",
    path    => ["/bin"],
    require => Exec["gettomcattarfile"],
  }

  file { "/etc/init.d/tomcat" :
  	content => template("tomcat/tomcat.initd"),
  	mode   =>  777,
  	group  => "root",
  	owner  => "root",
  	require => Exec["tomcat_untar"],
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

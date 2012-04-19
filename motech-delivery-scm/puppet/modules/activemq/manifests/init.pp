class activemq {

  exec { "getactivemqtar":
  	command => "/usr/bin/wget -O /tmp/activemq.tar.gz http://motechrepo.github.com/pub/motech/other/apache-activemq-5.5.1-bin.tar.gz"
  }


  exec { "activemq_untar":
    command => "tar xfz /tmp/activemq.tar.gz",
    user => "${motechUser}",
    cwd     => "/home/${motechUser}",
    creates => "/home/${motechUser}/apache-activemq-5.5.1",
    path    => ["/bin",],
    require => [Exec["${motechUser} homedir"], Exec["getactivemqtar"]],
  }

  file { "/etc/init.d/activemq" :
  	content => template("activemq/activemq-init.d"),
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
    path       => "/home/${motechUser}/apache-activemq-5.5.1/bin/activemq",
    ensure     => running,
    enable     => true,
    require => Exec["installservice"],
  }
}

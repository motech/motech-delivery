
class ant {
  exec {"getanttarfile" :
  	command => "/usr/bin/wget -O /tmp/ant.tar.gz http://motechrepo.github.com/pub/motech/other/apache-ant-1.8.2-bin.tar.gz"
   }
	
  exec { "ant_untar":
    command => "tar xfz /tmp/ant.tar.gz",
    user => "${motechUser}",
    cwd     => "/home/${motechUser}",
    creates => "/home/${motechUser}/apache-ant-1.8.2",
    path    => ["/bin",],
    require => [Exec['getanttarfile']],
  }
}

class ant {

  file {"/tmp/ant.tar.gz":
    source => "puppet:///modules/ant/apache-ant-1.8.2-bin.tar.gz",
  }
	
  exec { "ant_untar":
    command => "tar xfz /tmp/ant.tar.gz",
    user => "${motechUser}",
    cwd     => "/home/${motechUser}",
    creates => "/home/${motechUser}/apache-ant-1.8.2",
    path    => ["/bin",],
    require => File["/tmp/ant.tar.gz"],
  }
}
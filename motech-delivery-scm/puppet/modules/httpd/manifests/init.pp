
class httpd ( $httpdMachine, $httpdProxyPort, $httpdMasterHost, $httpdMasterPort, $httpdSlaveHost, $httpdSlavePort ) {
	package { "httpd" :
		ensure => "present",
	}

    if "${httpdMachine}" == 'failoverProxy' {
       exec {
            "backup_slave_conf":
                cwd     => "/etc/httpd/conf",
                command => "mv httpd.conf httpd.conf.backup",
       }

       file { "/etc/httpd/conf/httpd.conf":
           notify => Service["httpd"],
           content => template("httpd/httpd_slave.conf.erb"),
           mode   =>  644,
           require => Exec["backup_slave_conf"],
       }
    }

	service {"httpd" :
		ensure => "running",
		enable => true,
		require => Package["httpd"],
	}
	exec {"setconnectpermission":
		command => "/usr/sbin/setsebool -P httpd_can_network_connect on>/tmp/log.txt 2>&1;",
		user => "root",
	}


}

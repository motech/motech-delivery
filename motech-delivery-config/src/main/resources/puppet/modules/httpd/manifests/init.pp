
class httpd {
	package { "httpd" :
		ensure => "present",
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

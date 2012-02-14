
class monitor {
	file { "/home/${motechUser}/bin/monitorMotechService.sh":
		content => template("monitor/monitorMotechService.sh"),
		require => [Exec["${motechUser} homedir"], File["/home/${motechUser}/bin"]],
		mode   =>  777,
  		group  => "${motechUser}",
  		owner  => "${motechUser}",
	}
	cron { 'monitorMotechJob':
		command => "/home/${motechUser}/bin/monitorMotechService.sh>/var/tmp/monitorMotechService.log",
		user => "${motechUser}",
		minute => '*/15',
		require => File["/home/${motechUser}/bin/monitorMotechService.sh"],
	}
	file { "/home/${motechUser}/bin":
	    mode => 0755,
	    owner => "${motechUser}",
	    group => "${motechUser}",
	    ensure => directory,
	    recurse => true,
	    require => [Exec["${motechUser} homedir"]],
	} 		
}
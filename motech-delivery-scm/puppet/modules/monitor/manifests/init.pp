
class monitor {
	file { "/home/${motechUser}/bin/monitorTama.sh":
		content => template("monitor/monitorTama.sh"),
		require => [Exec["${motechUser} homedir"], File["/home/${motechUser}/bin"]],
		mode   =>  777,
  		group  => "${motechUser}",
  		owner  => "${motechUser}",
	}
	cron { 'monitorTamaJob':
		command => "/home/${motechUser}/bin/monitorTama.sh>/var/tmp/monitorTama.log",
		user => "${motechUser}",
		minute => '*',
		require => File["/home/${motechUser}/bin/monitorTama.sh"],
	}
	file { "/home/${motechUser}/bin":
	    mode => 0755,
	    owner => "${motechUser}",
	    group => "${motechUser}",
	    ensure => directory,
	    recurse => true,
	} 		
}
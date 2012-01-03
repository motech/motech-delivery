
class monitor {
	file { "/home/${motechUser}/bin/monitorTama.sh":
		source => "puppet:///modules/monitor/monitorTama.sh",
		require => [User["${motechUser}"]],
		mode   =>  777,
  		group  => "${motechUser}",
  		owner  => "${motechUser}",
	}
	cron { 'monitoTamaJob':
		command => "/home/${motechUser}/bin/monitorTama.sh>/var/tmp/monitorTama.log",
		user => "${motechUser}",
		minute => '*'
	}	
}

class monitor {
	file { "/home/tamasuper/bin/monitorTama.sh":
		source => "puppet:///modules/monitor/monitorTama.sh",
		require => [User["tamasuper"]],
		mode   =>  777,
  		group  => "tamasuper",
  		owner  => "tamasuper",
	}
	cron { 'monitoTamaJob':
		command => "/home/tamasuper/bin/monitorTama.sh>/var/tmp/monitorTama.log",
		user => tamasuper,
		minute => '*'
	}	
}

class motechquartz {
	exec { "createdbmotechquartz" :
		command => "mysql -u root -p${mysqlPassword} -e \"create database if not exists motechquartz;\"",
		require => [Package["mysql-server"], Package["mysql"]]
	}
	
}

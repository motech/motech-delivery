
class motechquartz {
	exec { "createdbmotechquartz" :
		command => "mysqladmin -u root PASSWORD password; mysql -u root -ppassword -e \"create database if not exists motechquartz;\"",
		require => [Package["mysql-server"], Package["mysql"]]
	}
	
}

class mysql {
	package { "mysql" :
		ensure  =>  "present"
	}
}

class mysqlserver {
	package { "mysql-server" :
		ensure  =>  "present"
	}
	service { "mysqld" :
		ensure => running,
		enable => true,
		require => Package["mysql-server"]
	}
}


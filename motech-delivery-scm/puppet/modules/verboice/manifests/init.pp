include repos::motech
	
class verboice {
	exec { "/usr/bin/yum -y install Verboice" :
		environment => "MYSQL_PASSWORD=$mysqlPassword",
		require => [Yumrepo["motech"], Exec["setmysqlpassword"], [Package["sox"]]],
		timeout => 0
	}

    exec { "configure_verboice.py" :
        cwd => "."
    }
}
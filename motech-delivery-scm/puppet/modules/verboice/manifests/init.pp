include repos::motech
	
class verboice {
	exec { "/usr/bin/yum -y install Verboice" :
		environment => "MYSQL_PASSWORD=$mysqlPassword",
		require => [Yumrepo["motech"], Exec["setmysqlpassword"]],
		timeout => 0
	}
}
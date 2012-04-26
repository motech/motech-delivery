include repos::motech
	
class verboice {

    file { 'configure_verboice':
        ensure => present,
        source => 'puppet:///modules/verboice/configure_verboice.py'
    }

	exec { "/usr/bin/yum -y install Verboice" :
		environment => "MYSQL_PASSWORD=$mysqlPassword",
		require => [Yumrepo["motech"], Exec["setmysqlpassword"], Package["sox"]],
		timeout => 0
	}

    exec { "configure_verboice" :
        require => File['configure_verboice']
    }
}
class verboice {
include repos::motech

    file { '/tmp/configure_verboice.py':
        ensure => present,
        source => 'puppet:///modules/verboice/configure_verboice.py'
    }

	exec { "/usr/bin/yum -y install Verboice" :
		environment => "MYSQL_PASSWORD=$mysqlPassword",
		require => [Yumrepo["motech"], Exec["setmysqlpassword"], Package["sox"]],
		timeout => 0
	}

    exec { "/tmp/configure_verboice.py" :
        require => [File['/tmp/configure_verboice.py'], Exec['/usr/bin/yum -y install Verboice']]
    }
}
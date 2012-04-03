class asterisk {
	package { "asterisk" :
		ensure => "present",
	}
	service { "asterisk" :
		enable => true,
		hasrestart => true,
		hasstatus => true,
		ensure => running,
		require => Package["asterisk"],
	}
}

class duplicity {
	package { "python-setuptools" :
		 ensure  =>  "present",
	}
	package { "duplicity" :
		ensure => "present",
		require => Exec["installboto"]
	}
	exec { "installboto" :
  		path    => "/usr/bin:/bin",
		command => "easy_install boto",	
		require => Package["python-setuptools"],	
	}
}

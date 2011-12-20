class java {
  file { "/tmp/jdk6.bin" :
  	source => "puppet:///modules/java/jdk-6u29-linux-x64-rpm.bin",  	
	mode => 775,
  }
  exec { "sun_jdk_6":
    path => ["/usr/bin","/bin","/usr/sbin","/sbin"],
    cwd => "/tmp",
    command => "echo |/tmp/jdk6.bin >/tmp/log.txt 2>&1 ; rm /tmp/jdk6.bin",
    require => File["/tmp/jdk6.bin"],
    user => "root"
  }
  file { "/etc/environment" :
  	content => "JAVA_HOME=/usr/java/jdk1.6.0_29/" 
  }
}


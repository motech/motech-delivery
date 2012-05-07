class ssl {
	package { "mod_ssl" :
		ensure  =>  "present"
	}
	file {"/home/${motechUser}/configure-ssl.sh" :
        content => template("ssl/configure-ssl.sh"),
        owner => "${motechUser}",
        group => "${motechUser}",
        mode   =>  764,
    }
	exec {"config-ssl" :
        require => File["/home/${motechUser}/configure-ssl.sh"],
	command => "sh /home/${motechUser}/configure-ssl.sh ${SSLCertificateFile} ${SSLCertificateKeyFile} "}
}

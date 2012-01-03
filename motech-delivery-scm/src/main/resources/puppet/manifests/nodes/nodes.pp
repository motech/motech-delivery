node default {
 $motechUser = "motech"
 $motechPassword = '$1$IW4OvlrH$Kui/55oif8W3VZIrnX6jL1'
 $mysqlPassword = "password"
 
 $monitor_adminPhoneNumbers = "9880xxxxxx 9880xxxxxx"
 $monitor_kookooKey = "KK76eca1f1a1229c2cc44ee858843b426d"
 $monitor_environment = "production"
 
 class{users : userName => "${motechUser}", password => "${motechPassword}" }	
	
 include java
 include git
 include httpd
 include ant
 include couchdb
 include mysql
 include mysqlserver
 include activemq
 include tomcat
 include duplicity
 include motechquartz
 include monitor
}

import "dev"
import "ci"

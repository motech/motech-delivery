 #-----------------------------MOTECH BOOTSTRAP---------------------------------------
 #  Save and exit to continue with setup.
 #------------------------------------------------------------------------------------

 #--------------------------------SETTINGS--------------------------------------------
 # user
 # to generate password hash use 'echo "password" | openssl passwd -1 -stdin'
 $motechUser = "motech"
 $motechPassword = '$1$IW4OvlrH$Kui/55oif8W3VZIrnX6jL1'

 # mysql
 $mysqlPassword = "password"

 # monitor
 $monitor_adminPhoneNumbers = "9880xxxxxx 9880xxxxxx"
 $monitor_kookooKey = "xxx"
 $monitor_environment = "production"

 # couchdbslave
 $masterServer = "127.0.0.1"
 $dbNames = "tama-web ananya"

 # postgres
 $postgresUser="postgres"
 $postgresPassword= '$1$IW4OvlrH$Kui/55oif8W3VZIrnX6jL1'

 # postgres replication
 $postgresMaster = "127.0.0.1"
 $postgresSlave = "127.0.0.1"

 #--------------------------------RESOURCES--------------------------------------------
 # comment out resources not required to be installed

 # class{users : userName => "${motechUser}", password => "${motechPassword}" }
 # class{couchdbslave : masterServer => "${masterServer}", dbNames => "${dbNames}" }
 # class{postgres : postgresUser => "${postgresUser}", postgresPassword => "${postgresPassword}" }
 # class{postgresReplication : postgresMaster => "${postgresMaster}", postgresSlave => "${postgresSlave}" }

 # include java
 # include git
 # include httpd    
 # include ant
 # include couchdb
 # include mysql
 # include mysqlserver
 # include activemq
 # include tomcat
 # include duplicity
 # include motechquartz
 # include monitor


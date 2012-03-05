 #-----------------------------MOTECH BOOTSTRAP---------------------------------------
 #  Save and exit to continue with setup.
 #------------------------------------------------------------------------------------

 #--------------------------------SETTINGS--------------------------------------------
 # operatingsystem
 $os = "centos5" #[centos5 | centos6]
 $word = "32b" #[32b,64b]
 $arch = "x64" # [x64|i586]

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
 $monitor_app_url = "http://localhost:8080/tama/login"

 # couchdb
 $couchMachine = "master" #[master | slave]
 $couchMaster = "127.0.0.1"
 $couchDbs = "tama-web ananya"

 # postgres
 $postgresUser="postgres"
 $postgresPassword= '$1$IW4OvlrH$Kui/55oif8W3VZIrnX6jL1'

 $postgresMachine = "master" #[master | slave]
 $postgresMaster = "127.0.0.1"
 $postgresSlave = "127.0.0.1"


 #--------------------------------RESOURCES--------------------------------------------
 # comment out resources not required to be installed

 # class{users : userName => "${motechUser}", password => "${motechPassword}" }
 # class{couchdb : couchMaster => "${couchMaster}", couchDbs => "${couchDbs}", couchMachine => "${couchMachine}" }
 # class{postgres : postgresUser => "${postgresUser}", postgresPassword => "${postgresPassword}", postgresMachine => "${postgresMachine}", postgresMaster => "${postgresMaster}", postgresSlave => "${postgresSlave}"}

 # include java
 # include git
 # include httpd    
 # include ant
 # include mysql
 # include mysqlserver
 # include activemq
 # include tomcat
 # include duplicity
 # include motechquartz
 # include monitor


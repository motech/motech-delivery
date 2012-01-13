#
#  MoTeCH BootStrap
#  Save and exit to continue with setup.
#

# ---user settings---
 $motechUser = "motech"
 $motechPassword = '$1$IW4OvlrH$Kui/55oif8W3VZIrnX6jL1' #  echo "password" | openssl passwd -1 -stdin

# ---mysql settings---
 $mysqlPassword = "password"

# ---monitor settings---
 include monitor # optional; comment it if service monitoring is not needed.
 $monitor_adminPhoneNumbers = "9880xxxxxx 9880xxxxxx"
 $monitor_kookooKey = "xxx"
 $monitor_environment = "production"

# ---couchdb replication---
 include couchdbslave
 $masterServer = "127.0.0.1"
 $dbNames = "db1 db2"
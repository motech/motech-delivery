#
#  Save and exit to continue with setup.
#
 $motechUser = "motech"
 $motechPassword = '$1$IW4OvlrH$Kui/55oif8W3VZIrnX6jL1' #  echo "password" | openssl passwd -1 -stdin
 $mysqlPassword = "password"


 $monitor_adminPhoneNumbers = "9880xxxxxx 9880xxxxxx"
 $monitor_kookooKey = "xxx"
 $monitor_environment = "production"

 include monitor # optional; comment it if service monitoring is not needed.
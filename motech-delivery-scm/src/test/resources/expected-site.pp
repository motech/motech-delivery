class { 'users': userName => 'motechsuper', }
class { 'mysql': rootPassword => 'password', }
node default {
  include users
  include mysql
  include activemq
  include couchdb
  include httpd
}
Exec {
  path => "/usr/bin:/usr/sbin/:/bin:/sbin:/usr/local/bin:/usr/local/sbin"
}

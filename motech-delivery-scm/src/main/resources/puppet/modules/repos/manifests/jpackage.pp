class repos::jpackage {
  file { "/usr/local/src/jpackage-utils-1.7.5-1jpp.noarch.rpm":
    source => "puppet://puppet/modules/repos/jpackage-utils-1.7.5-1jpp.noarch.rpm"
  }

  package { "jpackage-utils":
    provider => "rpm",
    ensure => "present",
    source => "/usr/local/src/jpackage-utils-1.7.5-1jpp.noarch.rpm",
    require => File["/usr/local/src/jpackage-utils-1.7.5-1jpp.noarch.rpm"]
  }
}

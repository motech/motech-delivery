class tanukiwrapper {
  file { "/usr/local/src/tanukiwrapper-3.2.3-1jpp.x86_64.rpm":
    #source => "puppet://puppet/modules/tanukiwrapper/tanukiwrapper-3.2.3-1jpp.x86_64.rpm"
    source => "/root/tama-config/puppet/modules/tanukiwrapper/files/tanukiwrapper-3.2.3-5.fc12.i686.rpm"
  }

  package { "tanukiwrapper":
    provider => "rpm",
    ensure => "present",
    source => "/usr/local/src/tanukiwrapper-3.2.3-1jpp.x86_64.rpm",
    require => File["/usr/local/src/tanukiwrapper-3.2.3-1jpp.x86_64.rpm"]
  }
}

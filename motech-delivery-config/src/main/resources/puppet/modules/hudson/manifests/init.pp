class hudson {
  include repos::hudson
  include java

  package { "hudson":
    ensure  =>  "present",
    require   => [Yumrepo[hudson], Exec["sun_jre_6"]]
  }

  service {"hudson":
      enable  => true,
      ensure  => "running",
      hasrestart=> true,
      require => Package["hudson"],
  }
}

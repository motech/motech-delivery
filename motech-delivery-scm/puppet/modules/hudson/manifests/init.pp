class hudson {
  include repos::hudson

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

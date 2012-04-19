class repos::motech {
  yumrepo { motech:
    descr    => 'Motech repo',
    baseurl  => 'http://motechrepo.github.com/pub/motech/6/x86_64/',
    enabled  => 1,
    gpgcheck => 0,
  }
}
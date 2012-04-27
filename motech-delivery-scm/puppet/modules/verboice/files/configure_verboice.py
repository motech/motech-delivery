#! /usr/bin/env python

import re, os, urllib2, urllib, cookielib

# installation location
asterisk_installation_dir = '/etc/asterisk'
verboice_installation_dir = '/opt/verboice'

# demo sip account details
email = 'test@test.com'
password = 'password'
application_name = 'testapp'
verboice_host = 'localhost:3000'
callback_url = 'http://10.2.3.4:3000/ivr/callback'
channel_name = 'demo_channel'
sip_username = 'bob'
sip_password = 'bob'
asterisk_host = 'localhost'

def enable_asterisk_manager():
    asterisk_manager_conf_file_path = '{0}/manager.conf'.format(asterisk_installation_dir)
    write_to_file(asterisk_manager_conf_file_path, re.sub(r'\[general\]' + os.linesep + 'enabled = no', '[general]{0}enabled = yes'.format(os.linesep), get_content_as_string(asterisk_manager_conf_file_path)))

def configure_sound_file_loc():

    verboice_conf_file_path = '{0}/config/asterisk.yml'.format(verboice_installation_dir)
    asterisk_conf_file_path = '{0}/asterisk.conf'.format(asterisk_installation_dir)

    asterisk_conf_content = get_content_as_string(asterisk_conf_file_path)
    sound_directory = '{0}/sounds'.format(re.search(r'astvarlibdir => (?P<path>.+)' + os.linesep, asterisk_conf_content).group('path'))

    verboice_conf_file_content = get_content_as_string(verboice_conf_file_path)
    content_with_updated_sound_loc = re.sub(r'sounds_dir: .+' + os.linesep, ('sounds_dir: {0}' + os.linesep).format(sound_directory), verboice_conf_file_content)
    content_with_updated_asterisk_loc = re.sub(r'config_dir: .+' + os.linesep, ('config_dir: {0}' + os.linesep).format(asterisk_installation_dir), content_with_updated_sound_loc)

    write_to_file(verboice_conf_file_path, content_with_updated_asterisk_loc)

def setup_verboice_restart_config():
    asterisk_conf_file_path = '{0}/extensions.conf'.format(asterisk_installation_dir)
    restart_config = """[verboice-restart]
exten => _.,1,AGI(agi://localhost:19000,${EXTEN})"""
    append_to_file(asterisk_conf_file_path, restart_config)

def setup_verboice_voice_plan(channel_id):
    asterisk_conf_file_path = '{0}/extensions.conf'.format(asterisk_installation_dir)

    voice_plan_config_template = """[verboice]
exten => #channel_id#,1,Answer
exten => #channel_id#,n,Wait(1)
exten => #channel_id#,n,AGI(agi://{0}:19000,,${{EXTEN}})"""

    voice_plan_config = re.sub(r'#channel_id#', channel_id, voice_plan_config_template.format(verboice_host.split(':')[0]))
    append_to_file(asterisk_conf_file_path, voice_plan_config)

def setup_asterisk_manager():
    manager_conf_file_path = '{0}/manager.conf'.format(asterisk_installation_dir)

    verboice_config = """[verboice]
secret=verboice
read=all
write=all"""

    append_to_file(manager_conf_file_path, verboice_config)

def add_channel_conf_to_etc_host(channel_id):
    append_to_file('/etc/hosts', '127.0.0.1     verboice_{0}-0'.format(channel_id))

def add_demo_sip_accounts():
    demo_sip_accounts = """[bob]
type=friend
username=bob
secret=bob
host=dynamic
context=verboice

[ivan]
type=friend
username=ivan
secret=ivan
host=dynamic
context=verboice"""

    demo_sip_accounts_dial_plan = """exten => 1234,1,Dial(SIP/bob,20)
exten => 4567,1,Dial(SIP/ivan,20)"""

    append_to_file('{0}/sip.conf'.format(asterisk_installation_dir), demo_sip_accounts)

    insert_after_line('{0}/extensions.conf'.format(asterisk_installation_dir), r'[default]', demo_sip_accounts_dial_plan + os.linesep)

def setup_verboice():

    def create_application():
        url = 'http://{0}/applications'.format(verboice_host)
        authenticity_token = re.search(r'authenticity_token.*value="(?P<authenticity_token>.*?)"', opener.open(url + '/new').read()).group('authenticity_token')
        opener.open(url, urllib.urlencode({'application[name]': application_name, 'application[mode]': 'callback_url', 'application[callback_url]': callback_url, 'authenticity_token': authenticity_token})).read()

    def login():
        url = 'http://{0}/accounts/sign_in'.format(verboice_host)
        cj = cookielib.CookieJar()
        opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
        opener.open(url,  urllib.urlencode({'account[email]': email, 'account[password]': password})).read()
        return opener

    def create_account():
        urllib2.urlopen('http://{0}/accounts'.format(verboice_host), urllib.urlencode({'account[email]': email, 'account[password]': password, 'account[password_confirmation]': password})).read()

    def create_channel():
        authenticity_token = re.search(r'authenticity_token.*value="(?P<authenticity_token>.*?)"', opener.open('http://{0}/channels/new?kind=sip'.format(verboice_host)).read()).group('authenticity_token')
        application_id = re.search(r'data-url=./applications/(?P<application_id>.*?).>', opener.open('http://{0}/applications'.format(verboice_host)).read()).group('application_id')
        opener.open('http://{0}/channels'.format(verboice_host), urllib.urlencode({'channel[name]': channel_name, 'channel[application_id]': application_id, 'channel[kind]': 'sip', 'channel[username]': sip_username, 'channel[password]': sip_password, 'channel[host][]': asterisk_host, 'channel[register][]': '1', 'channel[direction][]': 'both', 'authenticity_token': authenticity_token})).read()
	channel_id = re.search(r'/channels/(?P<channel_id>.*?)/edit"', opener.open('http://{0}/channels'.format(verboice_host)).read()).group('channel_id')
	return channel_id
		

    create_account()
    opener = login()
    create_application()
    return create_channel()

def get_content_as_string(file_path):
    return open(file_path, 'r').read()

def write_to_file(file_path, content):
    file = open(file_path, 'w')
    file.write(content)

def append_to_file(file_path, content):
    file = open(file_path, 'r+')
    file.write(file.read() + os.linesep + content)

def insert_after_line(file_path, line_to_insert_after, content):
    write_to_file(file_path, get_content_as_string(file_path).replace(line_to_insert_after, line_to_insert_after + os.linesep + content))

enable_asterisk_manager()
configure_sound_file_loc()
channel_id = setup_verboice()
setup_verboice_voice_plan(channel_id)
setup_verboice_restart_config()
setup_asterisk_manager()
add_channel_conf_to_etc_host(channel_id)
add_demo_sip_accounts()

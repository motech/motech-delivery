package org.motechproject.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final String algorithm = "MD5";
    private final String charsetName = "UTF-8";
    private String storedUsername;
    private String storedPassword;

    @Autowired
    public AuthenticationProvider(@Qualifier("batchCommonProperties") Properties batchProperties) {
        storedUsername = batchProperties.getProperty("app.user.name");
        storedPassword = batchProperties.getProperty("app.user.password");
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String password = (String) authentication.getCredentials();
        try {
            if (storedUsername.equals(encrypt(username)) && storedPassword.equals(encrypt(password))) {
                Collection<GrantedAuthority> a = new ArrayList<GrantedAuthority>();
                a.add(new GrantedAuthorityImpl("admin"));
                return new User("admin", "pass", true, true, true, true, a);
            }
        } catch (Exception ignored) {
        }
        throw new BadCredentialsException("Invalid password");
    }

    public String encrypt(String plaintext) throws Exception {
        AtomicReference<MessageDigest> msgDigest = new AtomicReference<MessageDigest>();
        String hashValue = null;
        try {
            msgDigest.set(MessageDigest.getInstance(algorithm));
            msgDigest.get().update(plaintext.getBytes(charsetName));
            byte rawByte[] = msgDigest.get().digest();
            hashValue = (new BASE64Encoder()).encode(rawByte);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm Exists");
        } catch (UnsupportedEncodingException e) {
            System.out.println("The Encoding Is Not Supported");
        }
        return hashValue;
    }

}

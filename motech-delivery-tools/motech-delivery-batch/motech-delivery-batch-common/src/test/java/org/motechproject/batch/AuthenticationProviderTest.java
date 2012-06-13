package org.motechproject.batch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

public class AuthenticationProviderTest {

    AuthenticationProvider authenticationProvider;

    @Before
    public void setUp(){
        authenticationProvider = new AuthenticationProvider(new Properties());
    }

    @Test
    public void shouldEncrypt() throws Exception {
        Assert.assertEquals("M868aiUyuEfwnjICeqACug==", authenticationProvider.encrypt("motech"));
        Assert.assertEquals("X03MO1qnZdYdgyfeuILPmQ==", authenticationProvider.encrypt("password"));
    }
}


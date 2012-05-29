package org.motechproject.batch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class AuthenticationProviderTest {
    @Test
    public void shouldEncrypt() throws Exception {
        Assert.assertEquals("M868aiUyuEfwnjICeqACug==", new AuthenticationProvider(new Properties()).encrypt("motech"));
        Assert.assertEquals("X03MO1qnZdYdgyfeuILPmQ==", new AuthenticationProvider(new Properties()).encrypt("password"));
    }
}


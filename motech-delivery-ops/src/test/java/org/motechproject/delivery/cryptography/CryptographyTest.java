package org.motechproject.delivery.cryptography;

import org.junit.Before;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static junit.framework.Assert.assertEquals;

public class CryptographyTest {

    private String secretKey;

    @Before
    public void setUp() {
        secretKey = "aknabhsekniv";
    }

    @Test
    public void shouldEncryptPassword_GivenAPlainTextPassword_AndSecretKey() throws IllegalBlockSizeException, IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException {
        String encryptedPassword = Cryptography.main(new String[]{secretKey, "plainTextPassword", "false"});
        assertEquals("lvVo1P5R3SdK0mJyr9MAVqSA2VK2k1mM", encryptedPassword);
    }

    @Test
    public void shouldDecryptPassword_GivenAnEncryptedPassword_AndSecretKey() throws IllegalBlockSizeException, IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException {
        String encryptedPassword = Cryptography.main(new String[]{secretKey, "lvVo1P5R3SdK0mJyr9MAVqSA2VK2k1mM", "true"});
        assertEquals("plainTextPassword", encryptedPassword);
    }
}

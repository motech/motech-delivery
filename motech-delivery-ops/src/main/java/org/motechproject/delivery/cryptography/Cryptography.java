package org.motechproject.delivery.cryptography;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Cryptography {

    public static final String UTF_8 = "UTF8";
    public static final String DES = "DES";
    private SecretKey key;
    private Cipher cipher;

    private Cryptography(String secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException {
        DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes(UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        key = keyFactory.generateSecret(keySpec);
        cipher = Cipher.getInstance(DES);
    }

    public static String main(String[] args) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, IllegalBlockSizeException, BadPaddingException {
        String secretKey = args[0];
        String password = args[1];
        boolean shouldDecrypt  = Boolean.parseBoolean(args[2]);

        Cryptography cryptography = new Cryptography(secretKey);
        if (shouldDecrypt) {
            return cryptography.decrypt(password);
        }
        return cryptography.encrypt(password);
    }

    public String encrypt(String plainTextPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        sun.misc.BASE64Encoder base64encoder = new BASE64Encoder();
        byte[] clearText = plainTextPassword.getBytes(UTF_8);

        cipher.init(Cipher.ENCRYPT_MODE, key);
        String encryptedPassword = base64encoder.encode(cipher.doFinal(clearText));
        System.out.println("Your encrypted password is " + encryptedPassword);
        return encryptedPassword;
    }

    private String decrypt(String encryptedPassword) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        sun.misc.BASE64Decoder base64decoder = new BASE64Decoder();
        byte[] encryptedPasswordBytes = base64decoder.decodeBuffer(encryptedPassword);

        cipher.init(Cipher.DECRYPT_MODE, key);
        String decryptedPassword = new String(cipher.doFinal(encryptedPasswordBytes));
        System.out.println("Your decrypted password is " + decryptedPassword);
        return decryptedPassword;
    }
}

package com.ellisiumx.bankingstack.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionUtils {

    // Preguiça de utilizar o método correto de criptografia para senhas
    // Funcionando ta valendo :P
    public static String encodeSHA256(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(data.getBytes(StandardCharsets.UTF_8));
            result = new String(Base64.getEncoder().encode(digest.digest()));
        } catch (NoSuchAlgorithmException e) { }
        finally {
            return result;
        }
    }

}

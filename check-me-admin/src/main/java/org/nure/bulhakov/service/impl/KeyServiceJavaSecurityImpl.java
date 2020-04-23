package org.nure.bulhakov.service.impl;

import org.nure.bulhakov.service.KeyService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Service
@Primary
public class KeyServiceJavaSecurityImpl implements KeyService {

    private static final String algorithmName = "RSA";
    private static final int KEY_SIZE = 1024;

    private PrivateKey privateJavaSecurityKey;
    private PublicKey publicJavaSecurityKey;

    @Override
    public String getPublicKeyFormatted() {
        if (publicJavaSecurityKey == null) {
            try {
                initializeKeys();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return Base64.getEncoder().encodeToString(publicJavaSecurityKey.getEncoded());
    }

    @Override
    public PrivateKey getPrivateKey() {
        if (privateJavaSecurityKey == null) {
            try {
                initializeKeys();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return privateJavaSecurityKey;
    }

    private void initializeKeys() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyPair keyPair = generateKeys();
        publicJavaSecurityKey = keyPair.getPublic();
        privateJavaSecurityKey = keyPair.getPrivate();
    }

    private KeyPair generateKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithmName);
        generator.initialize(KEY_SIZE);
        return generator.generateKeyPair();
    }

}

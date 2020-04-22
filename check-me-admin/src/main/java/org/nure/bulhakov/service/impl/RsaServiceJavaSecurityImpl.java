package org.nure.bulhakov.service.impl;

import org.nure.bulhakov.service.RsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.util.Base64;

@Service
@Primary
public class RsaServiceJavaSecurityImpl implements RsaService {

    private static final String ALGORITHM_NAME = "RSA/ECB/PKCS1Padding";

    private KeyServiceJavaSecurityImpl keyService;

    @Override
    public String decode(String encoded) throws Exception {
        PrivateKey privateKey = keyService.getPrivateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encodedBytes = Base64.getDecoder().decode(encoded);
        byte[] decoded = cipher.doFinal(encodedBytes);
        return new String(decoded);
    }

    @Autowired
    public void setKeyService(KeyServiceJavaSecurityImpl keyService) {
        this.keyService = keyService;
    }
}

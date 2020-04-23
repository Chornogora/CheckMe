package org.nure.bulhakov.service;

import java.security.PrivateKey;

public interface KeyService {

    String getPublicKeyFormatted();

    PrivateKey getPrivateKey();
}

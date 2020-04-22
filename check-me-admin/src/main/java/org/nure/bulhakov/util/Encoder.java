package org.nure.bulhakov.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Class that is used to encode information using md5.
 */
@Component
public class Encoder {

    private static final String ENCODING = "MD5";

    private final Logger logger = LoggerFactory.getLogger(org.nure.bulhakov.util.Encoder.class);

    public String encodeMd5(String line) {
        byte[] bytesOfMessage = line.getBytes(StandardCharsets.UTF_8);

        MessageDigest md;
        try {
            md = MessageDigest.getInstance(ENCODING);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Algorithm MD5 was not found");
            throw new UnsupportedOperationException(e);
        }

        byte[] encodedBytes = md.digest(bytesOfMessage);
        return byteToHex(encodedBytes);
    }

    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}

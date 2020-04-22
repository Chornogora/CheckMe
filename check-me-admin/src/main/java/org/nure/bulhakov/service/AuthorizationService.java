package org.nure.bulhakov.service;

public interface AuthorizationService {

    boolean authorize(String loginEncoded, String passwordEncoded) throws Exception;
}

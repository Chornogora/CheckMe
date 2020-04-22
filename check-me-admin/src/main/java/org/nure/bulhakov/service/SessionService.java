package org.nure.bulhakov.service;

public interface SessionService {

    String createSession();

    boolean isSessionValid(String sessionId);
}

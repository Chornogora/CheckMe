package org.nure.bulhakov.repository;

public interface SessionRepository {

    String createSession();

    boolean isSessionValid(String sessionId);

    void clearExpiredSessions();
}

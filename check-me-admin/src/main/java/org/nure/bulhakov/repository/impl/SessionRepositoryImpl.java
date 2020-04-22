package org.nure.bulhakov.repository.impl;

import org.nure.bulhakov.repository.SessionRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    private static final int SESSION_EXPIRATION_HOURS = 1;

    private Map<String, Date> sessions = new HashMap<>();

    @Override
    public String createSession() {
        String sessionId = UUID.randomUUID().toString();
        Date expirationDate = getExpirationDate();
        sessions.put(sessionId, expirationDate);
        return sessionId;
    }

    @Override
    public boolean isSessionValid(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    private Date getExpirationDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(Calendar.HOUR_OF_DAY, SESSION_EXPIRATION_HOURS);
        return calendar.getTime();
    }

    @Override
    public void clearExpiredSessions(){
        Date date = new Date(System.currentTimeMillis());
        Map<String, Date> newSessions = new HashMap<>();
        sessions.entrySet().stream()
                .filter(entry -> date.compareTo(entry.getValue()) > 0)
                .forEach(entry -> newSessions.put(entry.getKey(), entry.getValue()));
        sessions = newSessions;
    }
}

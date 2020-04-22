package org.nure.bulhakov.service.impl;

import org.nure.bulhakov.repository.SessionRepository;
import org.nure.bulhakov.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SessionServiceImpl implements SessionService {

    private static final int SESSION_CLEARING_MINUTES = 1;

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public String createSession(){
        return sessionRepository.createSession();
    }

    @Override
    public boolean isSessionValid(String sessionId){
        return sessionRepository.isSessionValid(sessionId);
    }

    @Autowired
    private void setClearingThread(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                sessionRepository.clearExpiredSessions();
                timer.schedule(this, getClearingDate());
            }
        };
        timer.schedule(task, getClearingDate());
    }

    private Date getClearingDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(Calendar.MINUTE, SESSION_CLEARING_MINUTES);
        return calendar.getTime();
    }
}

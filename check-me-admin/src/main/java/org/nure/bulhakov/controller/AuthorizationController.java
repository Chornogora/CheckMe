package org.nure.bulhakov.controller;

import org.nure.bulhakov.service.AuthorizationService;
import org.nure.bulhakov.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    private static final String SESSION_COOKIE_NAME = "SESSION_ID";

    private final AuthorizationService authorizationService;
    private final SessionService sessionService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService, SessionService sessionService) {
        this.authorizationService = authorizationService;
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<String> authorize(HttpServletResponse response, @RequestParam String login, @RequestParam String password) throws Exception {
        boolean authorizationResult = authorizationService.authorize(login, password);
        if (authorizationResult) {
            Cookie cookie = createSessionCookie();
            response.addCookie(cookie);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.FORBIDDEN);
    }

    private Cookie createSessionCookie() {
        String sessionId = sessionService.createSession();
        return new Cookie(SESSION_COOKIE_NAME, sessionId);
    }
}

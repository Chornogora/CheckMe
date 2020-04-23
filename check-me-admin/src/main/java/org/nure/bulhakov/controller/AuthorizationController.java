package org.nure.bulhakov.controller;

import org.nure.bulhakov.dto.AuthorizationDto;
import org.nure.bulhakov.model.SystemAdministrator;
import org.nure.bulhakov.service.AuthorizationService;
import org.nure.bulhakov.service.SessionService;
import org.nure.bulhakov.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    private final AuthorizationService authorizationService;
    private final SessionService sessionService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService, SessionService sessionService) {
        this.authorizationService = authorizationService;
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<String> authorize(HttpServletResponse response, @RequestBody AuthorizationDto dto) throws Exception {
        String login = dto.getLogin();
        String password = dto.getPasswordHash();
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
        return new Cookie(Constants.SESSION_COOKIE_NAME, sessionId);
    }
}

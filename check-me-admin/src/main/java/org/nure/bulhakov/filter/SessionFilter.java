package org.nure.bulhakov.filter;

import org.nure.bulhakov.service.SessionService;
import org.nure.bulhakov.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

public class SessionFilter implements Filter {

    private SessionService sessionService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String sessionId = Stream.of(request.getCookies())
                .filter(cookie -> cookie.getName().equals(Constants.SESSION_COOKIE_NAME))
                .findFirst()
                .orElseThrow(() -> new SecurityException("Access denied"))
                .getValue();
        if(sessionService.isSessionValid(sessionId)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            response.setStatus(403);
        }
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}

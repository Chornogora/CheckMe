package org.nure.bulhakov.configuration;

import org.nure.bulhakov.filter.SessionFilter;
import org.nure.bulhakov.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityFilterConfiguration {

    private static final String[] urlPatterns = {
            "/device/*",
            "/organization/*"
    };

    private final SessionService sessionService;

    @Autowired
    public SecurityFilterConfiguration(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Bean
    public FilterRegistrationBean<SessionFilter> securityFilterRegistration() {
        FilterRegistrationBean<SessionFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(createFilter());
        registration.addUrlPatterns(urlPatterns);
        return registration;
    }

    private SessionFilter createFilter() {
        SessionFilter sessionFilter = new SessionFilter();
        sessionFilter.setSessionService(sessionService);
        return sessionFilter;
    }
}

package org.nure.bulhakov.service.impl;

import org.nure.bulhakov.model.SystemAdministrator;
import org.nure.bulhakov.repository.SystemAdministratorRepository;
import org.nure.bulhakov.service.AuthorizationService;
import org.nure.bulhakov.service.RsaService;
import org.nure.bulhakov.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final SystemAdministratorRepository administratorRepository;
    private final RsaService rsaService;
    private final Encoder encoder;

    @Autowired
    private AuthorizationServiceImpl(SystemAdministratorRepository administratorRepository, RsaService rsaService, Encoder encoder) {
        this.administratorRepository = administratorRepository;
        this.rsaService = rsaService;
        this.encoder = encoder;
    }

    @Override
    public boolean authorize(String loginEncoded, String passwordEncoded) throws Exception {
        String login = rsaService.decode(loginEncoded);
        SystemAdministrator administrator = administratorRepository.findByLogin(login);

        if(administrator != null){
            String password = rsaService.decode(passwordEncoded);
            String passwordHashed = encoder.encodeMd5(password);
            return administrator.getPasswordHash().equals(passwordHashed);
        }
        return false;
    }
}

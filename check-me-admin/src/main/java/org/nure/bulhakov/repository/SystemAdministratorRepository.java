package org.nure.bulhakov.repository;

import org.nure.bulhakov.model.SystemAdministrator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SystemAdministratorRepository extends MongoRepository<SystemAdministrator, String> {

    SystemAdministrator findByLogin(String login);
}

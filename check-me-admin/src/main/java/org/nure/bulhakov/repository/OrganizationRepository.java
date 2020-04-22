package org.nure.bulhakov.repository;

import org.nure.bulhakov.model.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepository extends MongoRepository<Organization, String> {
}
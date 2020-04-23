package org.nure.bulhakov.service;

import org.nure.bulhakov.model.Organization;

import java.util.List;

public interface OrganizationService {

    Organization register(Organization organization);

    Organization update(Organization organization);

    Organization get(String id);

    List<Organization> getAll();

    Organization delete(Organization organization);
}

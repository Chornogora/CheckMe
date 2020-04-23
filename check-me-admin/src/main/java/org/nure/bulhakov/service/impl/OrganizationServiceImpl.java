package org.nure.bulhakov.service.impl;

import org.nure.bulhakov.model.Organization;
import org.nure.bulhakov.repository.OrganizationRepository;
import org.nure.bulhakov.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization register(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization update(Organization organization) {
        Optional<Organization> oldValue = organizationRepository.findById(organization.getId());
        if (oldValue.isPresent()) {
            return organizationRepository.save(organization);
        }
        throw new IllegalArgumentException("Cannot find organization to update");
    }

    @Override
    public Organization get(String id) {
        return organizationRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Cannot find organization")
        );
    }

    @Override
    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization delete(Organization organization) {
        Optional<Organization> oldValue = organizationRepository.findById(organization.getId());
        if(oldValue.isPresent()){
            organizationRepository.delete(organization);
            return oldValue.get();
        }
        throw new IllegalArgumentException("Cannot find organization to delete");
    }
}

package org.nure.bulhakov.service.impl;

import org.nure.bulhakov.dto.OrganizationDevicesDto;
import org.nure.bulhakov.model.CheckMachine;
import org.nure.bulhakov.model.Organization;
import org.nure.bulhakov.repository.CheckMachineRepository;
import org.nure.bulhakov.repository.OrganizationRepository;
import org.nure.bulhakov.service.CheckMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CheckMachineServiceImpl implements CheckMachineService {

    private final CheckMachineRepository checkMachineRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public CheckMachineServiceImpl(CheckMachineRepository checkMachineRepository, OrganizationRepository organizationRepository) {
        this.checkMachineRepository = checkMachineRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public CheckMachine register(CheckMachine checkMachine) {
        return checkMachineRepository.save(checkMachine);
    }

    @Override
    public CheckMachine update(CheckMachine checkMachine) {
        Optional<CheckMachine> oldValue = checkMachineRepository.findById(checkMachine.getId());
        if (oldValue.isPresent()) {
            return checkMachineRepository.save(checkMachine);
        }
        throw new IllegalArgumentException("Cannot find check machine to update");
    }

    @Override
    public CheckMachine get(String id) {
        return checkMachineRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Cannot find check machine")
        );
    }

    @Override
    public List<CheckMachine> getAll() {
        return checkMachineRepository.findAll();
    }

    @Override
    public CheckMachine delete(CheckMachine checkMachine) {
        Optional<CheckMachine> oldValue = checkMachineRepository.findById(checkMachine.getId());
        if(oldValue.isPresent()){
            checkMachineRepository.delete(checkMachine);
            return oldValue.get();
        }
        throw new IllegalArgumentException("Cannot find check machine to delete");
    }

    @Override
    @Transactional
    public CheckMachine pin(OrganizationDevicesDto dto) {
        String organizationId = dto.getOrganization().getId();
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(()->new IllegalArgumentException("Cannot find organization"));

        CheckMachine checkMachine = dto.getCheckMachine();
        List<CheckMachine> checkMachines = organization.getCheckMachines();
        if(checkMachines == null){
            checkMachines = new ArrayList<>();
        }
        if(!checkMachines.contains(checkMachine)) {
            checkMachines.add(checkMachine);
        }
        organization.setCheckMachines(checkMachines);
        organizationRepository.save(organization);

        checkMachine.setUsedBy(organization);
        checkMachine.setStatus(CheckMachine.STATUS.IN_USE);
        return checkMachineRepository.save(checkMachine);
    }
}

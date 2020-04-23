package org.nure.bulhakov.service;

import org.nure.bulhakov.dto.OrganizationDevicesDto;
import org.nure.bulhakov.model.CheckMachine;

import java.util.List;

public interface CheckMachineService {

    CheckMachine register(CheckMachine checkMachine);

    CheckMachine update(CheckMachine checkMachine);

    CheckMachine get(String id);

    List<CheckMachine> getAll();

    CheckMachine delete(CheckMachine checkMachine);

    CheckMachine pin(OrganizationDevicesDto dto);
}

package org.nure.bulhakov.dto;

import org.nure.bulhakov.model.CheckMachine;
import org.nure.bulhakov.model.Organization;

public class OrganizationDevicesDto {

    private Organization organization;

    private CheckMachine checkMachine;

    public OrganizationDevicesDto() {
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public CheckMachine getCheckMachine() {
        return checkMachine;
    }

    public void setCheckMachine(CheckMachine checkMachine) {
        this.checkMachine = checkMachine;
    }
}

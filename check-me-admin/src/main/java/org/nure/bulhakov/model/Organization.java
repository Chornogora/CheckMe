package org.nure.bulhakov.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "organizations")
public class Organization {

    @Id
    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    @DBRef
    @JsonIgnoreProperties("usedBy")
    private List<CheckMachine> checkMachines;

    @DBRef
    private SystemAdministrator registeredBy;

    public Organization() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SystemAdministrator getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(SystemAdministrator registeredBy) {
        this.registeredBy = registeredBy;
    }

    public List<CheckMachine> getCheckMachines() {
        return checkMachines;
    }

    public void setCheckMachines(List<CheckMachine> checkMachines) {
        this.checkMachines = checkMachines;
    }
}

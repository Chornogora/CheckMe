package org.nure.bulhakov.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "check_machines")
public class CheckMachine {

    @Id
    private String id;

    private String name;

    private Date dateAdded;

    @DBRef
    //@JsonIgnoreProperties("checkMachines")
    private Organization usedBy;

    private STATUS status;

    public CheckMachine() {
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

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Organization getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(Organization usedBy) {
        this.usedBy = usedBy;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public enum STATUS {FREE, IN_USE, BROKEN}
}

package org.nure.bulhakov.controller;

import org.nure.bulhakov.model.Organization;
import org.nure.bulhakov.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Organization> register(@RequestBody Organization organization) {
        Organization registered = organizationService.register(organization);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<Organization> update(@RequestBody Organization organization) {
        Organization updated = organizationService.update(organization);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Organization> getAll(String id) {
        Organization organization = organizationService.get(id);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Organization>> getAll() {
        List<Organization> organizations = organizationService.getAll();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Organization> delete(@RequestBody Organization organization) {
        Organization deleted = organizationService.delete(organization);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}

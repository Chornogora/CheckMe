package org.nure.bulhakov.controller;

import org.nure.bulhakov.dto.OrganizationDevicesDto;
import org.nure.bulhakov.model.CheckMachine;
import org.nure.bulhakov.service.CheckMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/device")
public class CheckMachineController {

    private final CheckMachineService checkMachineService;

    @Autowired
    public CheckMachineController(CheckMachineService checkMachineService) {
        this.checkMachineService = checkMachineService;
    }

    @PostMapping("/register")
    public ResponseEntity<CheckMachine> register(@RequestBody CheckMachine checkMachine) {
        CheckMachine registered = checkMachineService.register(checkMachine);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<CheckMachine> update(@RequestBody CheckMachine checkMachine) {
        CheckMachine updated = checkMachineService.update(checkMachine);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<CheckMachine> get(String id) {
        CheckMachine checkMachine = checkMachineService.get(id);
        return new ResponseEntity<>(checkMachine, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CheckMachine>> getAll() {
        List<CheckMachine> checkMachines = checkMachineService.getAll();
        return new ResponseEntity<>(checkMachines, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CheckMachine> delete(@RequestBody CheckMachine checkMachine) {
        CheckMachine deleted = checkMachineService.delete(checkMachine);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @PostMapping("/pin")
    public ResponseEntity<CheckMachine> pinToOrganization(@RequestBody OrganizationDevicesDto dto){
        CheckMachine checkMachine = checkMachineService.pin(dto);
        return new ResponseEntity<>(checkMachine, HttpStatus.OK);
    }
}

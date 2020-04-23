package org.nure.bulhakov.controller;

import org.nure.bulhakov.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {

    private final KeyService keyService;

    @Autowired
    public SecurityController(KeyService keyService) {
        this.keyService = keyService;
    }

    @GetMapping("/keys/get")
    public ResponseEntity<String> getPublicKeyFormatted() {
        String publicKey = keyService.getPublicKeyFormatted();
        return new ResponseEntity<>(publicKey, HttpStatus.OK);
    }
}

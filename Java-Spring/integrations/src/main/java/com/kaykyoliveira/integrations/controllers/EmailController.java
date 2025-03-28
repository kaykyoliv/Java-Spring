package com.kaykyoliveira.integrations.controllers;

import com.kaykyoliveira.integrations.dto.EmailDTO;
import com.kaykyoliveira.integrations.services.EmailService;
import com.kaykyoliveira.integrations.services.SendGridEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {

    @Autowired
    private EmailService service;
    @PostMapping
    public ResponseEntity<Void> send(@RequestBody EmailDTO dto){
        service.sendEmail(dto);
        return ResponseEntity.noContent().build();
    }
}

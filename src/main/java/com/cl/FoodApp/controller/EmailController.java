package com.cl.FoodApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cl.FoodApp.dto.EmailDetails;
import com.cl.FoodApp.service.EmailService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {
	@Autowired 
	private EmailService emailService;
	 
    // Sending a simple Email
    @PostMapping("/sendMail")
    public String sendMail(@RequestParam int id)
    {
        String status = emailService.sendSimpleMail(id);
 
        return status;
    }
 
    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details)
    {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }
}

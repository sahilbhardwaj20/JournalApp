package com.abacus.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    EmailService emailService;

    @Test
    void testmail(){
        emailService.sendEmail(
                "sahilbhardwaj20@gmail.com",
                "Testing",
                "Hello World :-)");
    }
}

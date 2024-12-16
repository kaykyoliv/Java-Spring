package com.kaykyoliveira.integrations.config;

import com.kaykyoliveira.integrations.services.EmailService;
import com.kaykyoliveira.integrations.services.MockEmailService;
import com.kaykyoliveira.integrations.services.SendGridEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}

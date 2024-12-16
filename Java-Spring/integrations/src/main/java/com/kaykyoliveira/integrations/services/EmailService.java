package com.kaykyoliveira.integrations.services;

import com.kaykyoliveira.integrations.dto.EmailDTO;

public interface EmailService {
    void sendEmail(EmailDTO dto);
}

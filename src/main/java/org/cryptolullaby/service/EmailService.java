package org.cryptolullaby.service;

import org.cryptolullaby.entity.Email;
import org.cryptolullaby.exception.EmailNotFoundException;
import org.cryptolullaby.repository.EmailRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService (EmailRepository emailRepository) {

        this.emailRepository = emailRepository;

    }

    public void save (Email email) {

        emailRepository.save(email);

    }

    public Email findById (String id) {

        return emailRepository
                .findById(id)
                .orElseThrow(() -> new EmailNotFoundException("We couldn't find an email with this id " + id));

    }

}

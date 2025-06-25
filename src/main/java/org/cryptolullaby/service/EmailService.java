package org.cryptolullaby.service;

import org.cryptolullaby.entity.Email;
import org.cryptolullaby.exception.EmailNotFoundException;
import org.cryptolullaby.model.enums.EmailType;
import org.cryptolullaby.repository.EmailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page <Email> findAll (Pageable pageable) {

        return emailRepository.findAll(pageable);

    }

}

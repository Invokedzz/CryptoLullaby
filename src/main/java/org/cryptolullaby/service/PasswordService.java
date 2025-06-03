package org.cryptolullaby.service;

import org.cryptolullaby.exception.DivergentPasswordsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public PasswordService (PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;

    }

    public String encode (String password) {

        return passwordEncoder.encode(password);

    }

    public boolean matches (String rawPassword, String encodedPassword) {

        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {

            throw new DivergentPasswordsException("Passwords do not match!");

        }

        return true;

    }

}

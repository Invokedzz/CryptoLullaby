package org.cryptolullaby.model.dto.users;

import org.cryptolullaby.entity.Interest;

import java.util.List;

public record InterestDTO (

        List <Interest> interests

) {}

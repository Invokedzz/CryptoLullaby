package org.cryptolullaby.model.dto.users;

import org.cryptolullaby.entity.Users;

public record UsernameEmailDTO (

        String username,

        String email

)

{

    public UsernameEmailDTO (Users user) {

        this (user.getUsername(), user.getEmail());

    }

}

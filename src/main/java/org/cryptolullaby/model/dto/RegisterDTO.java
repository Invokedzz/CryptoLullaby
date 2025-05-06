package org.cryptolullaby.model.dto;

public record RegisterDTO (

        String username,

        String email,

        String password,

        String repeatPassword,

        String imgUrl

) {}

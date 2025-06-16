package org.cryptolullaby.model.dto.general;

public record EmailDTO (

        String from,

        String[] to,

        String subject,

        String content

) {}

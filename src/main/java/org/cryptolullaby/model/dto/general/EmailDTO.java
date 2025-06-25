package org.cryptolullaby.model.dto.general;

import org.cryptolullaby.entity.Email;

public record EmailDTO (

        String from,

        String[] to,

        String subject,

        String content

)

{

    public EmailDTO (Email email) {

        this (email.getFrom(), email.getTo(), email.getSubject(), email.getContent());

    }

}

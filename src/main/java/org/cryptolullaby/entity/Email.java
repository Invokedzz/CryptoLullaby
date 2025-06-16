package org.cryptolullaby.entity;

import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.enums.EmailStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("email")
public class Email {

    @Id
    private String id;

    private String from;

    private String [] to;

    private String subject;

    private String content;

    private EmailStatus status;

    private LocalDateTime createdAt;

    public Email () {}

    public Email (String from, String [] to, String subject, String content, EmailStatus status, LocalDateTime createdAt) {

        this.from = from;

        this.to = to;

        this.subject = subject;

        this.content = content;

        this.status = status;

        this.createdAt = createdAt;

    }

    public Email (EmailDTO emailDTO) {

        this.from = emailDTO.from();

        this.to = emailDTO.to();

        this.subject = emailDTO.subject();

        this.content = emailDTO.content();

        this.status = EmailStatus.SENT;

        this.createdAt = LocalDateTime.now();

    }

    public String getId () {

        return id;

    }

    public String getFrom () {

        return from;

    }

    public void setFrom (String from) {

        this.from = from;

    }

    public String [] getTo () {

        return to;

    }

    public void setTo (String [] to) {

        this.to = to;

    }

    public String getSubject () {

        return subject;

    }

    public void setSubject (String subject) {

        this.subject = subject;

    }

    public String getContent () {

        return content;

    }

    public void setContent (String content) {

        this.content = content;

    }

    public EmailStatus getStatus () {

        return status;

    }

    public void setStatus (EmailStatus status) {

        this.status = status;

    }

    public LocalDateTime getCreatedAt () {

        return createdAt;

    }

    public void setCreatedAt (LocalDateTime createdAt) {

        this.createdAt = createdAt;

    }

}

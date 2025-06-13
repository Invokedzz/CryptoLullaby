package org.cryptolullaby.entity;

import org.cryptolullaby.model.enums.EmailStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("email")
public class Email {

    @Id
    private String id;

    private String from;

    private String to;

    private String subject;

    private String content;

    private EmailStatus status;

    private String userId;

    public Email () {}

    public Email (String from, String to, String subject, String content, EmailStatus status, String userId) {

        this.from = from;

        this.to = to;

        this.subject = subject;

        this.content = content;

        this.status = status;

        this.userId = userId;

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

    public String getTo () {

        return to;

    }

    public void setTo (String to) {

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

    public String getUserId () {

        return userId;

    }

    public void setUserId (String userId) {

        this.userId = userId;

    }

}

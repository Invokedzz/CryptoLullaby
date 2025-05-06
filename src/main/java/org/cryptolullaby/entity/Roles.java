package org.cryptolullaby.entity;

import org.cryptolullaby.model.enums.RolesTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("roles")
public class Roles {

    @Id
    private String id;

    private RolesTypeEnum name;

    public Roles () {}

    public Roles (String id, RolesTypeEnum name) {

        this.id = id;

        this.name = name;

    }

    public String getId () {

        return id;

    }

    public RolesTypeEnum getName () {

        return name;

    }

    public void setName (RolesTypeEnum name) {

        this.name = name;

    }

}

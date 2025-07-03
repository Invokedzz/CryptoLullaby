package org.cryptolullaby.entity;

import org.bson.types.ObjectId;
import org.cryptolullaby.model.enums.RolesName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("roles")
public class Roles {

    @Id
    private ObjectId id;

    private RolesName name;

    public Roles () {}

    public Roles (RolesName name) {

        this.name = name;

    }

    public Roles (ObjectId id, RolesName name) {

        this.id = id;

        this.name = name;

    }

    public ObjectId getId () {

        return id;

    }

    public RolesName getName () {

        return name;

    }

    public void setName (RolesName name) {

        this.name = name;

    }

}

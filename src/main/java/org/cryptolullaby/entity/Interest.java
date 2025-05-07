package org.cryptolullaby.entity;

import org.cryptolullaby.model.enums.InterestName;

public class Interest {

    private String id;

    private InterestName type;

    public Interest () {}

    public Interest (String id, InterestName type) {

        this.id = id;

        this.type = type;

    }

    public String getId () {

        return id;

    }

    public InterestName getType () {

        return type;

    }

    public void setType (InterestName type) {

        this.type = type;

    }

}

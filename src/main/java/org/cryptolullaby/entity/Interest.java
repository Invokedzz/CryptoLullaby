package org.cryptolullaby.entity;

import org.cryptolullaby.model.enums.InterestEnum;

public class Interest {

    private String id;

    private InterestEnum type;

    public Interest () {}

    public Interest (String id, InterestEnum type) {

        this.id = id;

        this.type = type;

    }

    public String getId () {

        return id;

    }

    public InterestEnum getType () {

        return type;

    }

    public void setType (InterestEnum type) {

        this.type = type;

    }

}

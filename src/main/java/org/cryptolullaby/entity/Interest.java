package org.cryptolullaby.entity;

import org.cryptolullaby.model.enums.InterestName;

public class Interest {

    private InterestName type;

    public Interest () {}

    public Interest (InterestName type) {

        this.type = type;

    }

    public InterestName getType () {

        return type;

    }

    public void setType (InterestName type) {

        this.type = type;

    }

}

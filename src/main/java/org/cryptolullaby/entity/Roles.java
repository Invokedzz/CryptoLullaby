package org.cryptolullaby.entity;

public class Roles {

    private String id;

    private String name;

    public Roles () {}

    public Roles (String id, String name) {

        this.id = id;

        this.name = name;

    }

    public String getId () {

        return id;

    }

    public String getName () {

        return name;

    }

    public void setName (String name) {

        this.name = name;

    }

}

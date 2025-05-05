package org.cryptolullaby.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("users")
public class Users {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;

    private String description;

    private String imgUrl;

    private List <Roles> roles;

    private List <Interest> interests;

    public Users () {}

    public Users (

            String id, String username, String email, String password, String description,
            String imgUrl, List <Roles> roles, List <Interest> interests
    )

    {

        this.id = id;

        this.username = username;

        this.email = email;

        this.password = password;

        this.description = description;

        this.imgUrl = imgUrl;

        this.roles = roles;

        this.interests = interests;

    }

    public String getId () {

        return id;

    }

    public String getUsername () {

        return username;

    }

    public void setUsername (String username) {

        this.username = username;

    }

    public String getEmail () {

        return email;

    }

    public void setEmail (String email) {

        this.email = email;

    }

    public String getPassword () {

        return password;

    }

    public void setPassword (String password) {

        this.password = password;

    }

    public String getDescription () {

        return description;

    }

    public void setDescription (String description) {

        this.description = description;

    }

    public String getImgUrl () {

        return imgUrl;

    }

    public void setImgUrl (String imgUrl) {

        this.imgUrl = imgUrl;

    }

    public List <Roles> getRoles () {

        return roles;

    }

    public void setRoles (List <Roles> roles) {

        this.roles = roles;

    }

    public List <Interest> getInterests () {

        return interests;

    }

    public void setInterests (List <Interest> interests) {

        this.interests = interests;

    }

}

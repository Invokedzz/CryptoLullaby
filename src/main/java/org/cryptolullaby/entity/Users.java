package org.cryptolullaby.entity;

import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("users")
public class Users {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;

    private Images img;

    private List <String> rolesId;

    private List <Interest> interests;

    private LocalDateTime createdAt;

    private Boolean isActive;

    public Users () {}

    public Users (

            String id, String username, String email, String password, Images img, List <String> rolesId,
            List <Interest> interests, LocalDateTime createdAt, Boolean isActive
    )

    {

        this.id = id;

        this.username = username;

        this.email = email;

        this.password = password;

        this.img = img;

        this.rolesId = rolesId;

        this.interests = interests;

        this.createdAt = createdAt;

        this.isActive = isActive;

    }

    public Users (RegisterDTO register) {

        this.username = register.username();

        this.email = register.email();

        this.password = register.password();

        this.img = new Images();

        this.createdAt = LocalDateTime.now();

        this.isActive = false;

    }

    public void editProfile (EditProfileDTO editProfileDTO) {

        if (editProfileDTO.password() != null) {

            this.password = editProfileDTO.password();

        }

        if (editProfileDTO.interests() != null && !editProfileDTO.interests().isEmpty()) {

            this.interests = editProfileDTO.interests();

        }

    }

    public String getId () {

        return id;

    }

    public String getUsername () {

        return username;

    }

    public String getEmail () {

        return email;

    }

    public String getPassword () {

        return password;

    }

    public void setPassword (String password) {

        this.password = password;

    }

    public Images getImg () {

        return img;

    }

    public void setImgUrl (Images img) {

        this.img = img;

    }

    public List <String> getRolesId () {

        return rolesId;

    }

    public void setRolesId (List <String> rolesId) {

        this.rolesId = rolesId;

    }

    public List <Interest> getInterests () {

        return interests;

    }

    public void setInterests (List <Interest> interests) {

        this.interests = interests;

    }

    public LocalDateTime getCreatedAt () {

        return createdAt;

    }

    public Boolean getIsActive () {

        return isActive;

    }

    public void activate () {

        this.isActive = true;

    }

    public void deactivate () {

        this.isActive = false;

    }

}
package org.cryptolullaby.entity;

import org.cryptolullaby.model.dto.RegisterDTO;
import org.cryptolullaby.model.dto.UpdateProfileDTO;
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

    private String imgUrl;

    private List <String> rolesId;

    private List <Interest> interests;

    private LocalDateTime createdAt;

    private Boolean isActive;

    public Users () {}

    public Users (

            String id, String username, String email, String password, String imgUrl, List <String> rolesId,
            List <Interest> interests, LocalDateTime createdAt, Boolean isActive
    )

    {

        this.id = id;

        this.username = username;

        this.email = email;

        this.password = password;

        this.imgUrl = imgUrl;

        this.rolesId = rolesId;

        this.interests = interests;

        this.createdAt = createdAt;

        this.isActive = isActive;

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

    public String getImgUrl () {

        return imgUrl;

    }

    public void setImgUrl (String imgUrl) {

        this.imgUrl = imgUrl;

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

    public Users (RegisterDTO register) {

        this.username = register.username();

        this.email = register.email();

        this.password = register.password();

        this.imgUrl = register.imgUrl();

        this.createdAt = LocalDateTime.now();

        this.isActive = false;

    }

    public void activate () {

        this.isActive = true;

    }

    public void deactivate () {

        this.isActive = false;

    }

    public void updateProfile (UpdateProfileDTO updateProfileDTO) {

        if (updateProfileDTO.email() != null) {

            this.email = updateProfileDTO.email();

        }

        if (updateProfileDTO.password() != null) {

            if (updateProfileDTO.password().equals(updateProfileDTO.confirmNewPassword())) {

                this.password = updateProfileDTO.password();

            }

        }

        if (updateProfileDTO.interests() != null) {

            this.interests = updateProfileDTO.interests();

        }

    }

}
package org.cryptolullaby.entity;

import org.cryptolullaby.model.dto.users.ProfileDTO;
import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.UsernameEmailDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ProfileStatus;
import org.postgresql.shaded.com.ongres.stringprep.Profile;
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

    private ProfileStatus status;

    private EntityType entityType;

    private Boolean isActive;

    public Users () {}

    public Users (

            String id, String username, String email, String password, Images img,
            List <String> rolesId, List <Interest> interests,
            LocalDateTime createdAt, ProfileStatus status, EntityType entityType, Boolean isActive
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

        this.status = status;

        this.entityType = entityType;

        this.isActive = isActive;

    }

    public Users (RegisterDTO register) {

        this.username = register.username();

        this.email = register.email();

        this.password = register.password();

        this.img = new Images();

        this.createdAt = LocalDateTime.now();

        this.status = ProfileStatus.PUBLIC;

        this.entityType = EntityType.USER;

        this.isActive = false;

    }

    public Users (ProfileDTO profileDTO) {

        this.username = profileDTO.username();

        this.email = profileDTO.email();

        this.img = profileDTO.img();

        this.interests = profileDTO.interests();

        this.createdAt = profileDTO.createdAt();

    }

    public Users (UsernameEmailDTO usernameEmailDTO) {

        this.username = usernameEmailDTO.username();

        this.email = usernameEmailDTO.email();

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

    public ProfileStatus getStatus () {

        return status;

    }

    public void setStatus (ProfileStatus status) {

        this.status = status;

    }

    public EntityType getEntityType () {

        return entityType;

    }

    public void setEntityType (EntityType entityType) {

        this.entityType = entityType;

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
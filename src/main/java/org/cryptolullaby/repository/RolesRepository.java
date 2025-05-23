package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Roles;
import org.cryptolullaby.model.enums.RolesName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends MongoRepository <Roles, String> {

    Optional <Roles> findByName (RolesName name);

}

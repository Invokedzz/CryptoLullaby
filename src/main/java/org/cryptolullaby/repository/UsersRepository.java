package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends MongoRepository <Users, String> {

    boolean existsByUsername (String username);

    boolean existsByEmail (String email);

    Page <Users> findByIdAndIsActive (String id, Boolean isActive, Pageable pageable);

    Optional <Users> findByEmailAndIsActive(String email, Boolean isActive);

}

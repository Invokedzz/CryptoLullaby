package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.users.UserInfoForScheduledDeletion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends MongoRepository <Users, String> {

    Page <Users> findByIdAndIsActive (String id, Boolean isActive, Pageable pageable);

    @Query(value = "{'username':  {$regex :  ?0, $options: 'i'}}")
    Page <Users> findByUsernameAndIsActive (String username, Boolean isActive, Pageable pageable);

    Optional <Users> findByEmailAndIsActive (String email, Boolean isActive);

    boolean existsByUsername (String username);

    boolean existsByEmail (String email);

    boolean existsByIdAndIsActive (String id, Boolean isActive);

    List <UserInfoForScheduledDeletion> findAllByIsActive (Boolean isActive);
}

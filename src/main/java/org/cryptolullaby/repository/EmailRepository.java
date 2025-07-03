package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Email;
import org.cryptolullaby.model.enums.EmailType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository <Email, String> {

    Page <Email> findAllByType (EmailType type, Pageable pageable);

}

package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository <Email, String> {}

package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository <Users, String> {}

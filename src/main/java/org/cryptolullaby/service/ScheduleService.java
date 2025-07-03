package org.cryptolullaby.service;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.repository.UsersRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleService {

    private final UsersRepository usersRepository;

    private final MongoTemplate mongoTemplate;

    private static final boolean IS_ACTIVE = true;

    private static final boolean IS_INACTIVE = false;

    public ScheduleService (UsersRepository usersRepository, MongoTemplate mongoTemplate) {

        this.usersRepository = usersRepository;

        this.mongoTemplate = mongoTemplate;

    }

    public void scheduleDateToDeleteDeactivatedUsers () {

        var inactiveUsersIdList = usersRepository.findAllByIsActive(IS_INACTIVE);

        for (var inactiveUserId : inactiveUsersIdList) {

            System.out.println(inactiveUserId);

            if (inactiveUserId.createdAt().getYear() < LocalDateTime.now().getYear()) {

                System.out.println(inactiveUserId.id());

                var query = getQuery(Criteria.where("id").is(inactiveUserId.id()));

                var update = getUpdate().set("isDeleted", IS_ACTIVE);

                mongoTemplate.updateFirst(query, update, Users.class);

            }

        }

    }

    private Query getQuery (Criteria criteria) {

        return new Query(criteria);

    }

    private Update getUpdate () {

        return new Update();

    }

}

package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends MongoRepository <Posts, String> {

    Page <Posts> findAllByIsActive (Boolean isActive, Pageable pageable);

    Page <Posts> findByTitle (String title, Pageable pageable);

}

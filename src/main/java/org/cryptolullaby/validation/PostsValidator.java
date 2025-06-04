package org.cryptolullaby.validation;

import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.exception.InvalidPostConditionException;
import org.cryptolullaby.exception.PostNotFoundException;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostsValidator {

    public void checkIfPostListWithCertainTitleExists (Page <Posts> posts) {

        if (posts.isEmpty()) {

            throw new PostNotFoundException("We weren't able to find any posts with this title!");

        }

    }

    public void checkIfPostIsActuallyActive (boolean isActive) {

        if (!isActive) {

            throw new InvalidPostConditionException("You cannot create or edit posts without them being active!");

        }

    }

}

package org.cryptolullaby.validation;

import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.exception.PostNotFoundException;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostsValidator {

    public void checkIfPostListWithCertainTitleExists (List <PostsDTO> posts) {

        if (posts.isEmpty()) {

            throw new PostNotFoundException("We weren't able to find any posts with this title!");

        }

    }

}

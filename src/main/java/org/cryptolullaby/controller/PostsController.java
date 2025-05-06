package org.cryptolullaby.controller;

import org.cryptolullaby.service.PostsService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {

    private final PostsService postsService;

    public PostsController (PostsService postsService) {

        this.postsService = postsService;

    }

}

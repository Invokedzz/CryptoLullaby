package org.cryptolullaby.controller;

import org.cryptolullaby.service.CommentsService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {

    private final CommentsService commentsService;

    public CommentsController (CommentsService commentsService) {

        this.commentsService = commentsService;

    }

}

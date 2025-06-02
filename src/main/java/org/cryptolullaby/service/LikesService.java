package org.cryptolullaby.service;

public interface LikesService {

    void likeACertainPost (String postId);

    void dislikeACertainPost (String postId);

    void likeACertainComment (String commentId);

    void dislikeACertainComment (String commentId);

}

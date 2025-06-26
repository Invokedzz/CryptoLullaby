package org.cryptolullaby.model.dto.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.cryptolullaby.validation.annotations.BlockHTML;
import org.hibernate.validator.constraints.Length;

public record CreateCommentDTO (

        @BlockHTML
        @NotBlank(message = "Commentary field cannot be blank!")
        @Length(min = 1, max = 255, message = "Commentary must have 1 to 255 characters!")
        @Pattern(regexp = "^(?!\\s*$).+", message = "Commentary can only contain numbers, letters and special characters!")
        String comment,

        @NotBlank(message = "User Id cannot be blank!")
        @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "UserId has an invalid ObjectId format!")
        String userId,

        @NotBlank(message = "Post Id cannot be blank!")
        @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "PostId has an invalid ObjectId format!")
        String postId,

        String parentId

) {}

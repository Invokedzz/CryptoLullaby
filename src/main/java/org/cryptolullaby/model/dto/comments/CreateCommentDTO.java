package org.cryptolullaby.model.dto.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CreateCommentDTO (

        @NotBlank(message = "Commentary field cannot be empty!")
        @Length(min = 1, max = 255, message = "Commentary must have 1 to 255 characters!")
        @Pattern(regexp = "^(?!\\s*$).+", message = "Commentary can only contain numbers, letters and special characters!")
        String comment,

        String userId,

        @NotBlank(message = "Post id cannot be blank!")
        String postId,

        String parentId

) {}

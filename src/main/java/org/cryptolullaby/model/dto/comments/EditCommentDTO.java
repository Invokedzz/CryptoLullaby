package org.cryptolullaby.model.dto.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.cryptolullaby.validation.annotations.BlockHTML;
import org.hibernate.validator.constraints.Length;

public record EditCommentDTO (

        @BlockHTML
        @Pattern(regexp = "(?s)^.{1,250}$")
        @NotBlank(message = "Comment cannot be blank!")
        @Length(min = 1, max = 250, message = "Message length cannot exceed 250 characters!")
        String comment

) {}

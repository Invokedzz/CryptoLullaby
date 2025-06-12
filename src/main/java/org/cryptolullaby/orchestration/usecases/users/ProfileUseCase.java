package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.model.dto.users.ProfileDTO;
import org.cryptolullaby.model.enums.EntityTypeName;
import org.cryptolullaby.service.*;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileUseCase implements IPaginationStructure <ProfileDTO, Users> {

    private final UsersService usersService;

    private final PostsService postsService;

    private final FollowService followService;

    private final LikesService likesService;

    public ProfileUseCase (UsersService usersService, PostsService postsService, FollowService followService, LikesService likesService) {

        this.usersService = usersService;

        this.postsService = postsService;

        this.followService = followService;

        this.likesService = likesService;

    }

    public PagedResponseDTO <ProfileDTO> getUserProfileById (String id, Pageable pageable) {

       var pages = usersService.findByIdAndIsActive(id, pageable);

        var users = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, users);

    }

    @Override
    public PagedResponseDTO <ProfileDTO> setupPaginationStructure (Page <Users> pages, List <ProfileDTO> elements) {

        return new PagedResponseDTO<>(

                elements,

                pages.getNumber(),

                pages.getSize(),

                pages.getTotalPages()

        );

    }

    @Override
    public List <ProfileDTO> getPagesContentAndRenderItToDTO (Page <Users> pages) {

        return pages
                .getContent()
                .stream()
                .map(user -> {

                    var posts = postsService.findPostByUserIdAndIsActive(user.getId());

                    var postDTOs = posts
                            .stream()
                            .map(
                                    post -> new PostsDTO(

                                            post.getImg(),
                                            post.getTitle(),
                                            post.getDescription(),
                                            post.getCreatedAt(),
                                            likesService.countNumberOfLikes(post.getId(), EntityTypeName.POST),
                                            user.getId()

                            ))
                            .toList();

                    var followers = followService.getUserFollowers(user.getId());

                    return new ProfileDTO(user, followers, postDTOs);

                })
                .toList();

    }

}

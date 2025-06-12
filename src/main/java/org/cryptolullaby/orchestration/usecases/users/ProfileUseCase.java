package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.ProfileDTO;
import org.cryptolullaby.model.enums.EntityTypeName;
import org.cryptolullaby.service.*;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProfileUseCase implements IPaginationStructure <ProfileDTO, Users> {

    private final UsersService usersService;

    private final PostsService postsService;

    private final FollowService followService;

    private final LikesService likesService;

    private final CloudinaryService cloudinaryService;

    private static final boolean DEFAULT_IMAGE_ICON = true;

    public ProfileUseCase (UsersService usersService, PostsService postsService, FollowService followService, LikesService likesService, CloudinaryService cloudinaryService) {

        this.usersService = usersService;

        this.postsService = postsService;

        this.followService = followService;

        this.likesService = likesService;

        this.cloudinaryService = cloudinaryService;

    }

    public PagedResponseDTO <ProfileDTO> getUserProfileById (String id, Pageable pageable) {

       var pages = usersService.findByIdAndIsActive(id, pageable);

        var users = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, users);

    }

    public void editUserProfile (String id, EditProfileDTO profileDTO) {

        var user = findUserById(id);

        var interests = sanitizeInterests(profileDTO.interests());

        var editedProfile = new EditProfileDTO(

                profileDTO.password(),
                interests

        );

        setupComponentsForProfileEdit(user, editedProfile);

        saveChangesInTheDatabase(user);

    }

    public void editUserImage (String id, MultipartFile file) {

        var user = findUserById(id);

        var img = setupProfileImage(file);

        user.setImgUrl(img);

        saveChangesInTheDatabase(user);

    }

    public void deactivateUserAccount (String id) {

        var user = findUserById(id);

        user.deactivate();

        saveChangesInTheDatabase(user);

    }

    @Override
    public PagedResponseDTO<ProfileDTO> setupPaginationStructure(Page<Users> pages, List<ProfileDTO> elements) {

        return new PagedResponseDTO<>(

                elements,

                pages.getNumber(),

                pages.getSize(),

                pages.getTotalPages()

        );

    }

    @Override
    public List <ProfileDTO> getPagesContentAndRenderItToDTO(Page<Users> pages) {

        return pages
                .getContent()
                .stream()
                .map(user -> {

                    var posts = postsService.findPostByUserIdAndIsActive(user.getId());

                    var postDTOs = posts.stream()
                            .map(post -> new PostsDTO(
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

    private Images setupProfileImage (MultipartFile file) {

       return cloudinaryService.renderImage(file, DEFAULT_IMAGE_ICON);

    }

    private Users findUserById (String id) {

        return usersService.findUserById(id);

    }

    private void saveChangesInTheDatabase (Users user) {

        usersService.save(user);

    }

    private void setupComponentsForProfileEdit (Users user, EditProfileDTO profileDTO) {

        user.editProfile(profileDTO);

    }

    private List <Interest> sanitizeInterests (List <Interest> interests) {

        return usersService.getSanitizedInterestList(interests);

    }

}

package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Follow;
import org.cryptolullaby.model.dto.follow.FollowDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.enums.FollowStatus;
import org.cryptolullaby.service.FollowService;
import org.cryptolullaby.service.UsersService;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowUseCase implements IPaginationStructure <FollowDTO, Follow> {

    private final FollowService followService;

    private final UsersService usersService;

    public FollowUseCase (FollowService followService, UsersService usersService) {

        this.followService = followService;

        this.usersService = usersService;

    }

    public PagedResponseDTO <FollowDTO> getAllOfUsersFollowers (String followingId, Pageable pageable) {

        var pages = followService.findAllByStatusEqualsToFollowingAndFollowingId(followingId, pageable);

        var follows = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, follows);

    }

    public PagedResponseDTO <FollowDTO> getAllFollowRequestsSentToACertainUser (String followingId, Pageable pageable) {

        var pages = followService.findAllFollowRequestsByStatusEqualsToPendingAndFollowingId(followingId, pageable);

        var follows = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, follows);

    }

    public PagedResponseDTO <FollowDTO> getAllFollowRequestsMadeByACertainUser (String followerId, Pageable pageable) {

        var pages = followService.findAllFollowRequestsByStatusEqualsToPendingAndFollowerId(followerId, pageable);

        var follows = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, follows);

    }

    public PagedResponseDTO <FollowDTO> getAllBlockedUsersBySomeCertainUser (String followingId, Pageable pageable) {

        var pages = followService.findAllByStatusEqualsToBlockedAndFollowingId(followingId, pageable);

        var follows = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, follows);

    }

    public void follow (FollowDTO followDTO) {

        followService.save(new Follow(followDTO));

    }

    public void acceptFollowRequest (String followerId) {

        var follower = followService.findByFollowerId(followerId);

        follower.setFollowStatus(FollowStatus.FOLLOWING);

        followService.save(follower);

    }

    public void rejectFollowRequest (String followerId) {

        var follower = followService.findByFollowerId(followerId);

        if (follower.getFollowStatus().equals(FollowStatus.PENDING) || follower.getFollowStatus().equals(FollowStatus.FOLLOWING)) {

            followService.delete(follower);

        }

    }

    public void block (String followerId) {

        var follower = followService.findByFollowerId(followerId);

        follower.setFollowStatus(FollowStatus.BLOCKED);

        followService.save(follower);

    }

    @Override
    public PagedResponseDTO <FollowDTO> setupPaginationStructure (Page <Follow> pages, List <FollowDTO> elements) {

        return new PagedResponseDTO<>(

                elements,

                pages.getNumber(),

                pages.getSize(),

                pages.getTotalPages()

        );

    }

    @Override
    public List <FollowDTO> getPagesContentAndRenderItToDTO (Page <Follow> pages) {

        return pages
                .getContent()
                .stream()
                .map(FollowDTO::new)
                .toList();

    }

}

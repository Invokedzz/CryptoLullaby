package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Follow;
import org.cryptolullaby.exception.InvalidFollowInviteException;
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
import java.util.Optional;

@Service
public class FollowUseCase implements IPaginationStructure <FollowDTO, Follow> {

    private final FollowService followService;

    private final UsersService usersService;//

    public FollowUseCase (FollowService followService, UsersService usersService) {

        this.followService = followService;

        this.usersService = usersService;

    }

    public PagedResponseDTO <FollowDTO> getAllOfUsersFollowers (String followingId, Pageable pageable) {

        var pages = findAllByStatusEqualsToFollowingAndFollowingId(followingId, pageable);

        var follows = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, follows);

    }

    public PagedResponseDTO <FollowDTO> getAllFollowRequestsSentToACertainUser (String followingId, Pageable pageable) {

        var pages = findAllFollowRequestsByStatusEqualsToPendingAndFollowingId(followingId, pageable);

        var follows = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, follows);

    }

    public PagedResponseDTO <FollowDTO> getAllFollowRequestsMadeByACertainUser (String followerId, Pageable pageable) {

        var pages = findAllFollowRequestsByStatusEqualsToPendingAndFollowerId(followerId, pageable);

        var follows = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, follows);

    }

    public PagedResponseDTO <FollowDTO> getAllBlockedUsersBySomeCertainUser (String followingId, Pageable pageable) {

        var pages = findAllByStatusEqualsToBlockedAndFollowingId(followingId, pageable);

        var follows = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, follows);

    }

    public void follow (FollowDTO followDTO) {

        checkIfFollowerIdAndFollowingIdAreValid(followDTO.followerId(), followDTO.followingId());

        var doesRelationExist = findByFollowerIdAndFollowingId(followDTO.followerId(), followDTO.followingId());

        if (doesRelationExist.isEmpty()) {

            followService.save(new Follow(followDTO));

        }

        doesRelationExist.ifPresent(follow -> {

            if (follow.getFollowStatus().equals(FollowStatus.BLOCKED)) {

                throw new InvalidFollowInviteException("You can't follow a user that blocked you!");

            }

            followService.deleteByFollowerIdAndFollowingId(
                    followDTO.followerId(),
                    followDTO.followingId()
            );

        });

    }

    public void acceptFollowRequest (String id) {

        var follower = findByFollowId(id);

        follower.setFollowStatus(FollowStatus.FOLLOWING);

        followService.save(follower);

    }

    public void rejectFollowRequest (String followerId) {

        var follower = findByFollowId(followerId);

        if (follower.getFollowStatus().equals(FollowStatus.PENDING) || follower.getFollowStatus().equals(FollowStatus.FOLLOWING)) {

            followService.delete(follower);

        }

    }

    public void block (String id) {

        var follower = findByFollowId(id);

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

    private Follow findByFollowId (String id) {

        return followService.findByFollowId(id);

    }

    private Page <Follow> findAllByStatusEqualsToBlockedAndFollowingId (String followingId, Pageable pageable) {

        return followService.findAllByStatusEqualsToBlockedAndFollowingId(followingId, pageable);

    }

    private Page <Follow> findAllFollowRequestsByStatusEqualsToPendingAndFollowerId (String followerId, Pageable pageable) {

        return followService.findAllFollowRequestsByStatusEqualsToPendingAndFollowerId(followerId, pageable);

    }

    private Page <Follow> findAllFollowRequestsByStatusEqualsToPendingAndFollowingId (String followingId, Pageable pageable) {

        return followService.findAllFollowRequestsByStatusEqualsToPendingAndFollowingId(followingId, pageable);

    }

    private void checkIfFollowerIdAndFollowingIdAreValid (String followerId, String followingId) {

        usersService.checkIfBothIdsAreValid(followerId, followingId);

    }

    private Optional <Follow> findByFollowerIdAndFollowingId (String followerId, String followingId) {

        return followService.findByFollowerIdAndFollowingId(followerId, followingId);

    }

    private Page <Follow> findAllByStatusEqualsToFollowingAndFollowingId (String followingId, Pageable pageable) {

        return followService.findAllByStatusEqualsToFollowingAndFollowingId(followingId, pageable);

    }

}

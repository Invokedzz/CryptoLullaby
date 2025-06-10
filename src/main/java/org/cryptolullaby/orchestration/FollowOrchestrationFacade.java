package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.follow.FollowDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.orchestration.usecases.users.FollowUseCase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FollowOrchestrationFacade {

    private final FollowUseCase followUseCase;

    public FollowOrchestrationFacade (FollowUseCase followUseCase) {

        this.followUseCase = followUseCase;

    }

    public PagedResponseDTO <FollowDTO> getAllOfUsersFollowers (String followingId, Pageable pageable) {

        return followUseCase.getAllOfUsersFollowers(followingId, pageable);

    }

    public PagedResponseDTO <FollowDTO> getAllFollowRequestsSentToACertainUser (String followingId, Pageable pageable) {

        return followUseCase.getAllFollowRequestsSentToACertainUser(followingId, pageable);

    }

    public PagedResponseDTO <FollowDTO> getAllFollowRequestsMadeByACertainUser (String followerId, Pageable pageable) {

        return followUseCase.getAllFollowRequestsMadeByACertainUser(followerId, pageable);

    }

    public PagedResponseDTO <FollowDTO> getAllBlockedUsersBySomeCertainUser (String followingId, Pageable pageable) {

        return followUseCase.getAllBlockedUsersBySomeCertainUser(followingId, pageable);

    }

    public void follow (FollowDTO followDTO) {

        followUseCase.follow(followDTO);

    }

    public void acceptFollowRequest (String followerId) {

        followUseCase.acceptFollowRequest(followerId);

    }

    public void rejectFollowRequest (String followerId) {

        followUseCase.rejectFollowRequest(followerId);

    }

    public void block (String followerId) {

        followUseCase.block(followerId);

    }

}

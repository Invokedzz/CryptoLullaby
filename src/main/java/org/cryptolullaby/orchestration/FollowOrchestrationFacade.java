package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.follow.FollowDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.orchestration.usecases.users.FollowUseCase;
import org.springframework.stereotype.Service;

@Service
public class FollowOrchestrationFacade {

    private final FollowUseCase followUseCase;

    public FollowOrchestrationFacade (FollowUseCase followUseCase) {

        this.followUseCase = followUseCase;

    }

    public PagedResponseDTO <FollowDTO> getAllOfUsersFollowers () {

        return followUseCase.getAllOfUsersFollowers();

    }

    public PagedResponseDTO <FollowDTO> getAllFollowRequestsSentToACertainUser () {

        return followUseCase.getAllFollowRequestsSentToACertainUser();

    }

    public PagedResponseDTO <FollowDTO> getAllFollowRequestsMadeByACertainUser () {

        return followUseCase.getAllFollowRequestsMadeByACertainUser();

    }

    public PagedResponseDTO <FollowDTO> getAllBlockedUsersBySomeCertainUser () {

        return followUseCase.getAllBlockedUsersBySomeCertainUser();

    }

    public void follow () {

        followUseCase.follow();

    }

    public void acceptFollowRequest () {

        followUseCase.acceptFollowRequest();

    }

    public void rejectFollowRequest () {

        followUseCase.rejectFollowRequest();

    }

    public void block () {

        followUseCase.block();

    }

}

package org.cryptolullaby.orchestration;

import org.cryptolullaby.orchestration.usecases.users.FollowUseCase;
import org.springframework.stereotype.Service;

@Service
public class FollowOrchestrationFacade {

    private final FollowUseCase followUseCase;

    public FollowOrchestrationFacade (FollowUseCase followUseCase) {

        this.followUseCase = followUseCase;

    }

    public void getAllOfUsersFollowers () {}

    public void getAllFollowRequestsSentToACertainUser () {}

    public void getAllFollowRequestsMadeByACertainUser () {}

    public void getAllBlockedUsersBySomeCertainUser () {}

    public void follow () {}

    public void acceptFollowRequest () {}

    public void rejectFollowRequest () {}

    public void block () {}

}

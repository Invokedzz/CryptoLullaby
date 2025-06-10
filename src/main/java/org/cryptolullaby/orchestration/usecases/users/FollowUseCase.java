package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Follow;
import org.cryptolullaby.model.dto.follow.FollowDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.service.FollowService;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowUseCase implements IPaginationStructure <FollowDTO, Follow> {

    private final FollowService followService;

    public FollowUseCase (FollowService followService) {

        this.followService = followService;

    }

    public PagedResponseDTO <FollowDTO> getAllOfUsersFollowers () {

        return null;

    }

    public PagedResponseDTO <FollowDTO> getAllFollowRequestsSentToACertainUser () {

        return null;

    }

    public PagedResponseDTO <FollowDTO> getAllFollowRequestsMadeByACertainUser () {

        return null;

    }

    public PagedResponseDTO <FollowDTO> getAllBlockedUsersBySomeCertainUser () {

        return null;

    }

    public void follow () {



    }

    public void acceptFollowRequest () {



    }

    public void rejectFollowRequest () {



    }

    public void block () {



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

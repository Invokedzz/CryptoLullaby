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

    @Override
    public PagedResponseDTO <FollowDTO> setupPaginationStructure (Page <Follow> pages, List <FollowDTO> elements) {

        return null;

    }

    @Override
    public List <FollowDTO> getPagesContentAndRenderItToDTO (Page <Follow> pages) {

        return List.of();

    }

}

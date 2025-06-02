package org.cryptolullaby.util;

import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPaginationStructure <DTO, Entity> {

    PagedResponseDTO <DTO> setupPaginationStructure (Page <Entity> pages, List <DTO> elements);

    List <DTO> getPagesContentAndRenderItToDTO (Page <Entity> pages);

}

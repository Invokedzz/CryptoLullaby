package org.cryptolullaby.orchestration.usecases.email;

import org.cryptolullaby.entity.Email;
import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.enums.EmailType;
import org.cryptolullaby.service.EmailService;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailManagerUseCase implements IPaginationStructure <EmailDTO, Email> {

    private final EmailService emailService;

    public EmailManagerUseCase (EmailService emailService) {

        this.emailService = emailService;

    }

    public Email findById (String id) {

        return findEmailById(id);

    }

    public PagedResponseDTO <EmailDTO> findAll (EmailType type, Pageable pageable) {

        var pages = emailService.findAll(
                type, pageable
        );

        var emails = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, emails);

    }

    @Override
    public PagedResponseDTO <EmailDTO> setupPaginationStructure(Page <Email> pages, List <EmailDTO> elements) {

        return new PagedResponseDTO<>(

                elements,

                pages.getNumber(),

                pages.getSize(),

                pages.getTotalPages()

        );

    }

    @Override
    public List <EmailDTO> getPagesContentAndRenderItToDTO (Page <Email> pages) {

        return pages
                .getContent()
                .stream()
                .map(EmailDTO::new)
                .toList();

    }

    private Email findEmailById (String id) {

        return emailService.findById(id);

    }

}

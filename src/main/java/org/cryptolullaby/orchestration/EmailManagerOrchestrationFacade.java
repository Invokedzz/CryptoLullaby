package org.cryptolullaby.orchestration;

import org.cryptolullaby.entity.Email;
import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.enums.EmailType;
import org.cryptolullaby.orchestration.usecases.email.EmailManagerUseCase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmailManagerOrchestrationFacade {

    private final EmailManagerUseCase emailUseCase;

    public EmailManagerOrchestrationFacade(EmailManagerUseCase emailUseCase) {

        this.emailUseCase = emailUseCase;

    }

    public Email findById (String id) {

        return emailUseCase.findById(id);

    }

    public PagedResponseDTO <EmailDTO> findAll (EmailType type, Pageable pageable) {

        return emailUseCase.findAll(type, pageable);

    }

}

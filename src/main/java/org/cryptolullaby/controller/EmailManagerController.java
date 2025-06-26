package org.cryptolullaby.controller;

import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.enums.EmailType;
import org.cryptolullaby.orchestration.EmailManagerOrchestrationFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain/email/manager")
public class EmailManagerController {

    private final EmailManagerOrchestrationFacade orchestrationFacade;

    public EmailManagerController (EmailManagerOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @GetMapping("/{id}")
    public ResponseEntity <EmailDTO> getEmailById (@PathVariable String id) {

        var email = orchestrationFacade.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new EmailDTO(email));

    }

    @GetMapping
    public ResponseEntity <PagedResponseDTO<EmailDTO>> findAllEmails (

            @RequestParam(required = false) EmailType type,

            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var aBunchOfEmails = orchestrationFacade.findAll(type, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(aBunchOfEmails);

    }

}

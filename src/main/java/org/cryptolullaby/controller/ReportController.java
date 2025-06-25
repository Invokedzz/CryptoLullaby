package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.general.SystemResponseDTO;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.orchestration.ReportOrchestrationFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/domain/report")
public class ReportController {

    private final ReportOrchestrationFacade orchestrationFacade;

    public ReportController (ReportOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @PutMapping
    public ResponseEntity <SystemResponseDTO> report (@Valid @RequestBody CreateReportDTO createReportDTO) {

        orchestrationFacade.report(createReportDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SystemResponseDTO(
                "User was successfully reported. Now wait until our team analyses your report request!"
        ));

    }

    @GetMapping("/{id}")
    public ResponseEntity <Void> reportById (@PathVariable String id) {

        orchestrationFacade.getReportById();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    public ResponseEntity <Void> allEqualsToPendingStatusAndReporterId () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    public ResponseEntity <Void> allEqualsToReportedStatusAndReporterId () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    public ResponseEntity <Void> confirmReportRequest () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    public ResponseEntity <Void> denyReportRequest () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}

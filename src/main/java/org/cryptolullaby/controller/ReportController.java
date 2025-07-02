package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.general.SystemResponseDTO;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.model.dto.report.ReportDTO;
import org.cryptolullaby.orchestration.ReportOrchestrationFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    /*
    *
    * TO DO: INSTEAD OF USING "TYPE" IN THE REQUEST BODY USE IT IN THE PATH VARIABLE
    * 02/07/2025
    *
    * */

    @PutMapping
    public ResponseEntity <SystemResponseDTO> report (@Valid @RequestBody CreateReportDTO createReportDTO) {

        orchestrationFacade.report(createReportDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SystemResponseDTO(
                "User was successfully reported. Now wait until our team analyses your report request!"
        ));

    }

    @GetMapping("/{id}")
    public ResponseEntity <ReportDTO> reportById (@PathVariable String id) {

        var report = orchestrationFacade.getReportById(id);

    //    return ResponseEntity.status(HttpStatus.OK).body(new ReportDTO(report));

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/{reporterId}/pending")
    public ResponseEntity <PagedResponseDTO<ReportDTO>> allEqualsToPendingStatusAndReporterId (

            @PathVariable String reporterId,

            @PageableDefault(size = 5, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var pendingReports = orchestrationFacade.getAllEqualsToPendingStatusAndReporterId(reporterId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(pendingReports);

    }

    @GetMapping("/{reporterId}/reported")
    public ResponseEntity <PagedResponseDTO<ReportDTO>> allEqualsToReportedStatusAndReporterId (

            @PathVariable String reporterId,

            @PageableDefault(size = 5, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var reports = orchestrationFacade.getAllEqualsToReportedStatusAndReporterId(reporterId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(reports);

    }

    @GetMapping("/pending")
    public ResponseEntity <PagedResponseDTO<ReportDTO>> allEqualsToPendingStatus (

            @PageableDefault(size = 5, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var reports = orchestrationFacade.getAllEqualsToPendingStatus(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(reports);

    }

    @GetMapping("/in-analysis")
    public ResponseEntity <PagedResponseDTO<ReportDTO>> allEqualsToInAnalysisStatus (

            @PageableDefault(size = 5, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var reports = orchestrationFacade.getAllEqualsToInAnalysisStatus(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(reports);

    }

    @GetMapping("/reported")
    public ResponseEntity <PagedResponseDTO<ReportDTO>> allEqualsToReportedStatus (

            @PageableDefault(size = 5, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var reports = orchestrationFacade.getAllEqualsToReportedStatus(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(reports);

    }

    @PostMapping("/confirm")
    public ResponseEntity <Void> confirmReportRequest (@Valid @RequestBody EmailDTO emailDTO) {

        orchestrationFacade.confirmReportRequest(emailDTO);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PostMapping("/deny")
    public ResponseEntity <Void> denyReportRequest (@Valid @RequestBody EmailDTO emailDTO) {

        orchestrationFacade.denyReportRequest(emailDTO);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    /*@PutMapping
    public ResponseEntity <Void> randomMethodName () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }*/

}

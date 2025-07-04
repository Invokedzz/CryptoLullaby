package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.general.SystemResponseDTO;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.model.dto.report.ReportDTO;
import org.cryptolullaby.model.dto.report.ReportPageDTO;
import org.cryptolullaby.model.dto.report.StoreReportCasesIdDTO;
import org.cryptolullaby.model.enums.ReportStatus;
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

        return ResponseEntity.status(HttpStatus.OK).body(new ReportDTO(report));

    }

    @GetMapping("/pending")
    public ResponseEntity <PagedResponseDTO<ReportPageDTO>> allEqualsToPendingStatus (

            @PageableDefault(size = 5, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var reports = orchestrationFacade.getAllEqualsToPendingStatus(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(reports);

    }

    @GetMapping("/in-analysis")
    public ResponseEntity <PagedResponseDTO<ReportPageDTO>> allEqualsToInAnalysisStatus (

            @PageableDefault(size = 5, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var reports = orchestrationFacade.getAllEqualsToInAnalysisStatus(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(reports);

    }

    @GetMapping("/reported")
    public ResponseEntity <PagedResponseDTO<ReportPageDTO>> allEqualsToReportedStatus (

            @PageableDefault(size = 5, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var reports = orchestrationFacade.getAllEqualsToReportedStatus(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(reports);

    }

    @PostMapping("/process")
    public ResponseEntity <Void> processReportStatus (

            @RequestParam ReportStatus status,

            @RequestBody StoreReportCasesIdDTO reportCases

    )

    {

        orchestrationFacade.processReportRequest(status, reportCases);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/{reportedId}")
    public ResponseEntity <Void> limitAccessOrBanUserAfterACertainAmountOfReports (@PathVariable String reportedId) {

        orchestrationFacade.limitAccessOrBanUserAfterACertainAmountOfReports(reportedId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}

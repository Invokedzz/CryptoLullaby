package org.cryptolullaby.controller;

import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.orchestration.ReportOrchestrationFacade;
import org.cryptolullaby.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain/report")
public class ReportController {

    private final ReportOrchestrationFacade orchestrationFacade;

    public ReportController (ReportOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @PutMapping
    public ResponseEntity <Void> report () {

        orchestrationFacade.report();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping
    public ResponseEntity <Void> allReportsMadeToAnEntity (@RequestParam EntityType entity) {

        orchestrationFacade.getAllReportsMadeToAnEntity();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/{id}/")
    public ResponseEntity <Void> allReportsMadeToAnEntityById (

            @PathVariable String id,

            @RequestParam EntityType entity

    )

    {

        orchestrationFacade.getAllReportsMadeToAnEntityById();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity <Void> reportById (@PathVariable String id) {

        orchestrationFacade.getReportById();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping
    public ResponseEntity <Void> deleteReportByIdAndEntity () {

        orchestrationFacade.deleteReportByIdAndEntity();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}

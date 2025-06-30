package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.model.dto.report.ReportDTO;
import org.cryptolullaby.model.dto.users.UsernameEmailDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.service.CommentsService;
import org.cryptolullaby.service.PostsService;
import org.cryptolullaby.service.ReportService;
import org.cryptolullaby.service.UsersService;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportUseCase implements IPaginationStructure <ReportDTO, Report> {

    private final ReportService reportService;

    private final UsersService usersService;

    private final PostsService postsService;

    private final CommentsService commentsService;

    public ReportUseCase (ReportService reportService, UsersService usersService, PostsService postsService, CommentsService commentsService) {

        this.reportService = reportService;

        this.usersService = usersService;

        this.postsService = postsService;

        this.commentsService = commentsService;

    }

    public void report (CreateReportDTO createReportDTO) {

        reportService.save(new Report(createReportDTO));

    }

    public PagedResponseDTO <ReportDTO> getAllEqualsToPendingStatusAndReporterId (String reporterId, Pageable pageable) {

        var pages = reportService.findAllByStatusEqualsToPendingAndReporterId(
                reporterId, pageable
        );

        var reports = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, reports);

    }

    public PagedResponseDTO <ReportDTO> getAllEqualsToReportedStatusAndReporterId (String reporterId, Pageable pageable) {

        var pages = reportService.findAllByStatusEqualsToReportedAndReporterId(
               reporterId, pageable
        );

        var reports = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, reports);

    }

    public PagedResponseDTO <ReportDTO> getAllEqualsToPendingStatus (Pageable pageable) {

        var pages = reportService.findAllByStatusEqualsToPending(pageable);

        var reports = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, reports);

    }

    public PagedResponseDTO <ReportDTO> getAllEqualsToReportedStatus (Pageable pageable) {

        var pages = reportService.findAllByStatusEqualsToReported(pageable);

        var reports = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, reports);

    }

    public Report getReportById (String id) {

        return findReportById(id);

    }

    public void confirmReportRequest (EmailDTO emailDTO) {



    }

    public void denyReportRequest (EmailDTO emailDTO) {



    }

    @Override
    public PagedResponseDTO <ReportDTO> setupPaginationStructure (Page <Report> pages, List <ReportDTO> elements) {

        return new PagedResponseDTO<>(

                elements,

                pages.getNumber(),

                pages.getSize(),

                pages.getTotalPages()

        );

    }

    @Override
    public List <ReportDTO> getPagesContentAndRenderItToDTO (Page <Report> pages) {

        /*
        *
        * To do: Adjust this garbage method as soon as possible :(
        * 26/06/2025
        * FIXED: 28/05/2025
        * */

        return pages
                .getContent()
                .stream()
                .map(report -> {

                    var reporter = usersService.findUserById(report.getReporterId());

                    var reported = usersService.findUserById(report.getReportedId());

                    List <UsernameEmailDTO> listOfUsers = List.of(
                            new UsernameEmailDTO(reporter),
                            new UsernameEmailDTO(reported)
                    );

                    return new ReportDTO(report, listOfUsers);

                })
                .toList();

    }

    private Report findReportById (String id) {

        return reportService.findReportById(id);

    }

    private void reportSaveValidation (String reportedId, EntityType entity) {

        switch (entity) {

            case USER -> {

                return;

            }

            case POST -> {

            }

            case COMMENT -> {



            }

            default -> throw new IllegalStateException("Unexpected value: " + entity);

        };

    }

}

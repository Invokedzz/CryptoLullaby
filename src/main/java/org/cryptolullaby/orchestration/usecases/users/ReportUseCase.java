package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.model.dto.report.ReportPageDTO;
import org.cryptolullaby.model.dto.report.StoreReportCasesIdDTO;
import org.cryptolullaby.model.dto.users.UsernameEmailDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ReportStatus;
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
public class ReportUseCase implements IPaginationStructure <ReportPageDTO, Report> {

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

    public PagedResponseDTO <ReportPageDTO> getAllEqualsToPendingStatus (Pageable pageable) {

        var pages = reportService.findAllByStatusEqualsToPending(pageable);

        var reports = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, reports);

    }

    public PagedResponseDTO <ReportPageDTO> getAllEqualsToReportedStatus (Pageable pageable) {

        var pages = reportService.findAllByStatusEqualsToReported(pageable);

        var reports = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, reports);

    }

    public PagedResponseDTO <ReportPageDTO> getAllEqualsToInAnalysisStatus (Pageable pageable) {

        var pages = reportService.findAllByStatusEqualsToInAnalysis(pageable);

        var reports = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, reports);

    }

    public Report getReportById (String id) {

        return findReportById(id);

    }

    public void confirmReportRequest (StoreReportCasesIdDTO reportCases) {

        /*
        * TODO: METHOD RULES
        * 1. the emails can't repeat themselves
        * 2. They must be related to the reporterId
        * 3. And if the Set solution fails, don't let the id repeat themselves
        */

        var setOfIds = reportCases.id();

        for (var id : setOfIds) {

            var report = reportService.findReportOptionalById(id);

            if (report.isPresent()) {

                report.get().setStatus(ReportStatus.REPORTED);

                reportService.save(report.get());

            }

        }

    }

    public void denyReportRequest (StoreReportCasesIdDTO reportCases) {

        var setOfIds = reportCases.id();

        for (var id : setOfIds) {

            var report = reportService.findReportOptionalById(id);

            if (report.isPresent()) {

                report.get().setStatus(ReportStatus.DENIED);

                reportService.save(report.get());

            }

        }

    }

    public void randomMethodName () {



    }

    @Override
    public PagedResponseDTO <ReportPageDTO> setupPaginationStructure (Page <Report> pages, List <ReportPageDTO> elements) {

        return new PagedResponseDTO<>(

                elements,

                pages.getNumber(),

                pages.getSize(),

                pages.getTotalPages()

        );

    }

    @Override
    public List <ReportPageDTO> getPagesContentAndRenderItToDTO (Page <Report> pages) {

        /*
        *
        * Todo: Adjust this garbage method as soon as possible :(
        * 26/06/2025
        * FIXED: 28/05/2025
        * */

        return pages
                .getContent()
                .stream()
                .map(report -> {

                    var reporter = usersService.findUserByIdOrElseThrow(report.getReporterId());

                    var reported = usersService.findUserByIdOrElseThrow(report.getReportedId());

                    List <UsernameEmailDTO> listOfUsers = List.of(
                            new UsernameEmailDTO(reporter),
                            new UsernameEmailDTO(reported)
                    );

                    return new ReportPageDTO(report, listOfUsers);

                })
                .toList();

    }

    private Report findReportById (String id) {

        return reportService.findReportById(id);

    }

    private boolean reportSaveValidation (String reportedId, EntityType entity) {

        switch (entity) {

            case USER -> {

                return usersService.doesUserExist(reportedId);

            }

            case POST -> {

                return postsService.doesPostExist(reportedId);

            }

            case COMMENT -> {

                return commentsService.doesCommentExist(reportedId);

            }

            default -> throw new IllegalStateException("Unexpected value: " + entity);

        }

    }

}

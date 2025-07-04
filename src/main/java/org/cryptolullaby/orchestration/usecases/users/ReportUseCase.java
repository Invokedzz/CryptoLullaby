package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.exception.EmailAlreadyExistsException;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.model.dto.report.ReportPageDTO;
import org.cryptolullaby.model.dto.report.StoreReportCasesIdDTO;
import org.cryptolullaby.model.dto.users.UsernameEmailDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ProfileStatus;
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

        var pages = findAllByStatusEqualsToPending(pageable);

        var reports = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, reports);

    }

    public PagedResponseDTO <ReportPageDTO> getAllEqualsToReportedStatus (Pageable pageable) {

        var pages = findAllByStatusEqualsToReported(pageable);

        var reports = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, reports);

    }

    public PagedResponseDTO <ReportPageDTO> getAllEqualsToInAnalysisStatus (Pageable pageable) {

        var pages = findAllByStatusEqualsToInAnalysis(pageable);

        var reports = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, reports);

    }

    public Report getReportById (String id) {

        return findReportById(id);

    }

    public void processReportStatusChangeRequest (ReportStatus status, StoreReportCasesIdDTO reportCases) {

        /*
         * TODO: METHOD RULES
         * 1. the emails can't repeat themselves
         * 2. They must be related to the reporterId
         * 3. And if the Set solution fails, don't let the id repeat themselves
         * 4. Use the ReportStatus as a @RequestParam in order to avoid repetition
         */

        var hasAnyDuplicatedEmail = checkIfToFieldArrayHasAnyDuplicate(reportCases.email().to());

        if (!hasAnyDuplicatedEmail) {

            var setOfIds = reportCases.id();

            for (var id : setOfIds) {

                var optionalReport = findReportOptionalById(id);

                switch (status) {

                    case REPORTED, DENIED -> receiveOptionalReportSetTheProperStatusThenSave(status, optionalReport);

                    default -> throw new IllegalStateException("Unexpected value: " + status);

                }

            }

            return;

        }

        throw new EmailAlreadyExistsException("In order to send the email package, please, remove the duplicated emails!");

    }

    public void limitAccessOrBanUserAfterACertainAmountOfReports (String reportedId) {

        var counter = countNumberOfTimesAUserHasBeenReported(reportedId);

        if (counter >= 10) {

            var user = findUserByIdOrElseThrow(reportedId);

            if (counter >= 30) {

                user.getStatus().add(1, ProfileStatus.BANNED);

                saveChangesRelatedToUserInTheDatabase(user);

                return;

            }

            user.getStatus().add(1, ProfileStatus.LIMITED_ACCESS);

            saveChangesRelatedToUserInTheDatabase(user);

        }

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

                    var reporter = findUserByIdOrElseThrow(report.getReporterId());

                    var reported = findUserByIdOrElseThrow(report.getReportedId());

                    List <UsernameEmailDTO> listOfUsers = List.of(
                            new UsernameEmailDTO(reporter),
                            new UsernameEmailDTO(reported)
                    );

                    return new ReportPageDTO(report, listOfUsers);

                })
                .toList();

    }

    private Page <Report> findAllByStatusEqualsToPending (Pageable pageable) {

        return reportService.findAllByStatusEqualsToPending(pageable);

    }

    private Page <Report> findAllByStatusEqualsToReported (Pageable pageable) {

        return reportService.findAllByStatusEqualsToReported(pageable);

    }

    private Page <Report> findAllByStatusEqualsToInAnalysis (Pageable pageable) {

        return reportService.findAllByStatusEqualsToInAnalysis(pageable);

    }

    private Report findReportById (String id) {

        return reportService.findReportById(id);

    }

    private long countNumberOfTimesAUserHasBeenReported (String reportedId) {

        return reportService.countNumberOfTimesAUserHasBeenReported(reportedId);

    }

    private Optional <Report> findReportOptionalById (String id) {

        return reportService.findReportOptionalById(id);

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

    private boolean checkIfToFieldArrayHasAnyDuplicate (String [] to) {

        Set <String> hashSet = new HashSet<>();

        for (String email : to) {

            /*
            * If the hashset.add() is false, it means an element got duplicated, and it's not going to be
            * added in the hashset.
            *
            */

            if (!hashSet.add(email)) {

                return true;

            }

        }

        return false;

    }

    private void receiveOptionalReportSetTheProperStatusThenSave (ReportStatus status, Optional <Report> optionalReport) {

        if (optionalReport.isPresent()) {

            var report = optionalReport.get();

            report.setStatus(status);

            reportService.save(report);

        }

    }

    private Users findUserByIdOrElseThrow (String id) {

        return usersService.findUserByIdOrElseThrow(id);

    }

    private void saveChangesRelatedToUserInTheDatabase (Users user) {

        usersService.save(user);

    }

}

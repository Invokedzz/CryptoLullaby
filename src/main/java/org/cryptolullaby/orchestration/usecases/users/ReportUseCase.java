package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.model.dto.report.ReportDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.service.CommentsService;
import org.cryptolullaby.service.PostsService;
import org.cryptolullaby.service.ReportService;
import org.cryptolullaby.service.UsersService;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void getAllEqualsToPendingStatusAndReporterId () {}

    public void getAllEqualsToReportedStatusAndReporterId () {}

    public void getReportById () {}

    public void confirmReportRequest () {}

    public void denyReportRequest () {}

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

        return pages
                .getContent()
                .stream()
                .map(ReportDTO::new)
                .toList();

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

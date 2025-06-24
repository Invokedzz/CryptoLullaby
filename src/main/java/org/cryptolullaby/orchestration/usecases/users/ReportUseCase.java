package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.report.ReportDTO;
import org.cryptolullaby.service.ReportService;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportUseCase implements IPaginationStructure <ReportDTO, Report> {

    private final ReportService reportService;

    public ReportUseCase (ReportService reportService) {

        this.reportService = reportService;

    }

    public void report () {}

    public void getAllReportsMadeToAnEntity () {}

    public void getAllReportsMadeToAnEntityById () {}

    public void getReportById () {}

    public void deleteReportByIdAndEntity () {}

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

}

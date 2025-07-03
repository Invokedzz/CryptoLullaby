package org.cryptolullaby.model.dto.report;

import org.cryptolullaby.model.dto.general.EmailDTO;

import java.util.Set;

public record StoreReportCasesIdDTO (

        Set <String> id,

        EmailDTO email

) {}

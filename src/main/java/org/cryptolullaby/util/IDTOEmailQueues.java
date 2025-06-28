package org.cryptolullaby.util;

import org.cryptolullaby.model.dto.general.EmailDTO;

public interface IDTOEmailQueues {

    void sendEmailAfterReportCaseHasBeenDecided (EmailDTO emailDTO);

}

package org.cryptolullaby.util;

import org.cryptolullaby.model.dto.general.EmailDTO;

public interface IDTOEmailQueuesMethods {

    void sendEmailAfterReportCaseHasBeenDecided (EmailDTO emailDTO);

}

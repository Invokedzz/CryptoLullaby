package org.cryptolullaby.util;

import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.model.enums.InterestName;

import java.util.List;
import java.util.stream.Collectors;

public interface IUserInterest {

    default List <Interest> getSanitizedInterestList (List <Interest> interests) {

        /*
         *
         *  To do: fix the NullPointerIssue that is happening with interests.stream()
         *   09/05/2025
         *
         *   FIXED 12/05/2025
         *
         * */

        if (interests != null) {

            var sanitizedList =
                    interests.stream()
                            .filter(i -> i.getType() != null && !i.getType().getLabel().isBlank())
                            .collect(Collectors.toList());

            if (sanitizedList.isEmpty()) {

                sanitizedList.add(new Interest(InterestName.NONE));

            }

            return sanitizedList;

        }

        return List.of(new Interest(InterestName.NONE));

    }

}

package org.cryptolullaby.model.dto;

public record ExchangeResultsDTO (

        String acronym,

        Integer id,

        String name,

        String operating_mic,

        String type,

        String url

) {}

package com.bottega.promoter.concert.api.rest;

record CreateConcertRequestDto(
        String title,
        String date,
        String promoterId
) {
}

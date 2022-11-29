package com.bottega.vendor.concert.api.rest;

record CreateConcertRequestDto(
        String title,
        String date,
        String vendorId
) {
}

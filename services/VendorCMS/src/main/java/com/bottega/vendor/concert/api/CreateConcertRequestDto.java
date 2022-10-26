package com.bottega.vendor.concert.api;

record CreateConcertRequestDto(
        String title,
        String dateTime,
        String vendorId
) {
}

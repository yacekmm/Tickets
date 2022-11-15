package com.bottega.vendor.concertRead;

import com.bottega.vendor.concert.domain.Concert;

public record ConcertDto(
        String id,
        String title,
        String date
) {

    public static ConcertDto from(Concert concert) {
        return new ConcertDto(concert.getId().asString(), concert.getTitle().getValue(), concert.getDate().getUtcDate().toString());
    }
}

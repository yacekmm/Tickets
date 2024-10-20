package com.bottega.promoter.concertRead;

import com.bottega.promoter.concert.domain.ConcertId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "concert_price")
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConcertPrice {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ConcertId concertId;
    private Integer price;
}

package com.bottega.promoter.concertRead;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import static com.bottega.sharedlib.config.ApiVersions.V1;

@RestController
@AllArgsConstructor
public class ConcertReadRestController {

    private final ConcertReadService concertReadService;

    @GetMapping(path = V1 + "/concert/promoter/{promoterId}")
    @ResponseBody
    public List<ConcertDto> getConcerts(
            @PathVariable("promoterId") String promoterId) {

        return concertReadService.findConcertsForPromoter(promoterId).stream()
                .map(ConcertDto::from)
                .toList();
    }

}

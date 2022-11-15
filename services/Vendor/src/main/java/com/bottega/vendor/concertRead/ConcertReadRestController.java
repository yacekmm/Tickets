package com.bottega.vendor.concertRead;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bottega.sharedlib.config.ApiVersions.V1;

@RestController
@AllArgsConstructor
public class ConcertReadRestController {

    private final ConcertReadService concertReadService;

    @GetMapping(path = V1 + "/concert/vendor/{vendorId}")
    @ResponseBody
    public List<ConcertDto> getConcerts(
            @PathVariable("vendorId") String vendorId) {

        return concertReadService.findConcertsForVendor(vendorId).stream()
                .map(ConcertDto::from)
                .toList();
    }

}

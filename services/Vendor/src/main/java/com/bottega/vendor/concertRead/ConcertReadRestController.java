package com.bottega.vendor.concertRead;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bottega.sharedlib.config.ApiVersions.V1;

@RestController
@AllArgsConstructor
public class ConcertReadRestController {

    private final ConcertFinder concertFinder;

    //TODO: Api Test
    @GetMapping(path = V1 + "/concert/vendor/{vendorId}")
    @ResponseBody
    public List<ConcertDto> createConcert(
            @PathVariable("vendorId") String vendorId) {

        return concertFinder.findByVendorIdOrderByDateAsc(vendorId).stream()
                .map(ConcertDto::from)
                .toList();
    }

}

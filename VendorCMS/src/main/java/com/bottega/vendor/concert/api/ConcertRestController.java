package com.bottega.vendor.concert.api;

import com.bottega.vendor.config.ApiVersions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiVersions.V1 + "/concert" )
public class ConcertRestController {
}

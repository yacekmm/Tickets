package com.bottega.promoter.infra.client;

import com.bottega.sharedlib.config.ServicesProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import static java.time.Duration.ofSeconds;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@Slf4j
public class WebClientsConfig {

    @Bean
    public WebClient pricingWebClient(ServicesProperties servicesProperties) {

        final HttpClient httpClient = HttpClient.create()
                .responseTimeout(ofSeconds(servicesProperties.getRequestTimeoutInSeconds()))
                .doOnError(
                        (req, throwable) -> log.info("Error on HTTP Request. Req: {}, error: {}", req.fullPath(), throwable.toString()),
                        (res, throwable) -> log.info("Error on HTTP Response. Res: {}, error: {}", res, throwable.toString()));
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultHeader(ACCEPT, APPLICATION_JSON_VALUE)
                .baseUrl(servicesProperties.getPricing().url())
                .build();
    }

}

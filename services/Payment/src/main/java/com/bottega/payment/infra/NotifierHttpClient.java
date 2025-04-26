package com.bottega.payment.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class NotifierHttpClient {

    private final WebClient notifierClient;

    public void send(HttpClientReq req) {
        notifierClient.post()
                .uri("/v1/email")
                .bodyValue(req)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

}

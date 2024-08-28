package com.bottega.promoter.concert.fixtures;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.core.model.RequestResponsePact;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.http.HttpHeader.httpHeader;

@Component
public class PricingStubs {

    public static final String PATH = "/api/v1/item/123/price-factor/percentage";
    public static final String METHOD = "POST";
    public static final int RES_STATUS = 200;
    public static final HttpHeaders HEADERS = new HttpHeaders(httpHeader("Content-Type", "application/json"));
    public static final String REQ_BODY = """
            {
              "percentage": 10
            }
            """;
    public static final String RESP_BODY = """
            [
              {
                "price": 9000,
                "factors": [
                  {
                    "type": "PERCENTAGE",
                    "value": 10
                  }
                ]
              }
            ]
            """;

    public RequestResponsePact stubApplyPercentageDiscount(PactDslWithProvider builder) {

        Map<String, String> headersMap = HEADERS.all().stream()
                .collect(Collectors.toMap(HttpHeader::key, HttpHeader::firstValue));

        return builder
                .given("price for item exists", "itemId", "123")
                .uponReceiving("a discount request")
                .path(PATH)
                .method(METHOD)
                .headers(headersMap)
                .body(REQ_BODY)
                .willRespondWith()
                .status(RES_STATUS)
                .headers(headersMap)
                .body(RESP_BODY)
                .toPact();
    }

    public void stubApplyPercentageDiscount(WireMockServer pricingMockServer) {
        pricingMockServer.stubFor(WireMock.request(METHOD, WireMock.urlEqualTo(PATH))
                        .withRequestBody(WireMock.equalToJson(REQ_BODY))
                .willReturn(WireMock.aResponse()
                        .withStatus(RES_STATUS)
                        .withHeaders(HEADERS)
                        .withBody(RESP_BODY)));
    }
}

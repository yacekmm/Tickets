package com.bottega.vendor.concert.api;

import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.groovy.util.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bottega.vendor.config.ApiVersions.V1;
import static com.bottega.vendor.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT, properties = "server.port=9090")
public class CreateConcert_RestApiTest {

    @Autowired
    ConcertRepo concertRepo;

    @Test
    public void createConcert_creates_onValidRequest() {
        //given
        String title = "concert-title";
        String dateTime = TEST_TIME_PLUS_30_DAYS.toString();
        String vendorId = "some-id";

        //when
        RestAssured.port = 9090;
        Response response = RestAssured
                .given()
                .basePath(V1)
                .contentType(JSON)
                .body(Maps.of(
                        "title", title,
                        "dateTime" , dateTime,
                        "vendorId", vendorId
                ))
                .post("/concert")
                ;

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.asString()).isEqualTo("ddd");
        assertThat(concertRepo.findAll()).hasSize(1);
    }
}

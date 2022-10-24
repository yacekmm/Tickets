package com.bottega.vendor.concert.api;

import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.config.ApiVersions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.groovy.util.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bottega.vendor.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CreateConcert_RestApiTest {

    ConcertRepo concertRepo;

    @Test
    public void createConcert_creates_onValidRequest() {
        //given
        String title = "concert-title";
        String dateTime = TEST_TIME_PLUS_30_DAYS.toString();

        //when
        Response response = RestAssured
                .given()
                .basePath(ApiVersions.V1)
                .body(Maps.of(
                        "title", title,
                        "dateTime" , dateTime
                ))
                .post("/concert")
                ;

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getInt("result")).isEqualTo(44);
        assertThat(concertRepo.findAll()).hasSize(1);
    }
}

package com.bottega.vendor.concert.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateConcert_RestApiTest {

@Test
public void should(){
    //given
    Assertions.fail();

    //when


    //then
}
//    @Test
//    public void createConcert_creates_onValidRequest() {
//        //given
//        String title = "concert-title";
//        String dateTime = TestClockConfig
//        //when
//        Response response = RestAssured
//                .given()
//                .basePath(ApiVersions.V1)
//                .body(Maps.of(
//                        "title", title,
//                        "dateTime" , dateTime
//                ))
//                .post("/concert")
//                ;
//
//        //then
//        assertThat(response.statusCode()).isEqualTo(200);
//        assertThat(response.jsonPath().getInt("result")).isEqualTo(44);
//        assertThat(calcRepository.findAll()).hasSize(1);
//    }
}

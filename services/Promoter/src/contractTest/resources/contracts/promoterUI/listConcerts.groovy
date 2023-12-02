package contracts.promoter.percentageDiscount

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description """
        Request to list concerts from promoter-ui
        """

    request {

        method GET()

        url '/api/v1/concert/promoter/promoter-id'

        headers {
            accept applicationJson()
        }
    }

    response {
        status 200
        headers {
            contentType applicationJson()
        }
        body([
                [
                        id   : $(
                                producer(regex(uuid())),
                                consumer(anyUuid())
                        ),
                        title: $(
                                producer('Rihanna in Rome'),
                                consumer('Rihanna in Rome')
                        ),
                        date : $(
                                producer(anyNonBlankString()),
                                consumer('2042-03-07')
                        )
                ],
                [
                        id   : $(
                                producer(regex(uuid())),
                                consumer(anyUuid())
                        ),
                        title: $(
                                producer('Rock concert 2'),
                                consumer('Rock concert 2')
                        ),
                        date : $(
                                producer(anyNonBlankString()),
                                consumer('2022-04-06')
                        )
                ]
        ])
        bodyMatchers {
            jsonPath('[0].title', byEquality())
            jsonPath('[1].title', byEquality())
//            jsonPath('$.toyDetails[*].dimensions.width', byRegex(nonBlank()))
//            jsonPath('$.toyDetails[*].dimensions.length', byRegex(nonBlank()))
        }
    }
}

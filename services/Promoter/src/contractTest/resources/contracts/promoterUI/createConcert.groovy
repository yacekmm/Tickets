package contracts.promoter.percentageDiscount

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description """
        Request to create new concert from promoter-ui
        """

    request {

        method POST()

        url '/api/v1/concert'

        headers {
            contentType applicationJson()
            accept applicationJson()
        }

        body(
                title: $(
                        producer('some title'),
                        consumer(anyNonBlankString())
                ),
                date: $(
                        producer('2042-03-07T07:20:00Z'),
                        consumer(anyNonBlankString())
                ),
                promoterId: anyNonBlankString()
        )
    }

    response {
        status 200
        headers {
            contentType applicationJson()
        }
        body(
                id: $(
                        producer(regex(uuid())),
                        consumer(anyUuid())
                )
        )
    }
}

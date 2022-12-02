package contracts.vendor.percentageDiscount

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description """
        Request to create new concert from vendor-ui
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
                        producer('too short'),
                        consumer(anyNonBlankString())
                ),
                date: $(
                        producer('1987-03-07'),
                        consumer(anyNonBlankString())
                ),
                vendorId: anyNonBlankString()
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

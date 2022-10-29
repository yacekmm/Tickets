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
                title: 'some title',
                dateTime: '2042-03-07T07:20:00Z',
                vendorId: anyNonBlankString()
        )
    }

    response {
        status 200
        headers {
            contentType applicationJson()
        }
        body(
                id: regex(uuid())
        )
    }
}

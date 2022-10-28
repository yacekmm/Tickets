package contracts.vendor.percentageDiscount

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description """
        Request to create new concert rom vendor-ui
        """

    request {

        method POST()

        url '/api/v1/concert'

        headers {
            contentType applicationJson()
            accept applicationJson()
//            header 'Authorization': value(
//                    consumer(anyAlphaNumeric()),
//                    producer(execute('authToken()'))
//            )
        }

//        body(
//                title: 'some title',
//                dateTime: anyDateTime(),
//                vendorId: anyNonBlankString()
//        )
    }

    response {
        status 200
        headers {
            contentType applicationJson()
        }
//        body(

//                        concertId: regex(uuid())

//        )
    }
}

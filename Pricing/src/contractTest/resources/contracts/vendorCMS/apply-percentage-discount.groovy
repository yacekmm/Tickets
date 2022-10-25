package contracts.vendorCMS

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description """
        Request to apply discounting percentage factor to a concert price
        """

    request {
        method POST()
        url "/api/v1/item/concert-id/price-factor/percentage"
        headers {
            contentType applicationJson()
//            header 'Authorization': value(
//                    consumer(anyAlphaNumeric()),
//                    producer(execute('authToken()'))
//            )
        }
        body(
                percentage: 10
        )
    }

    response {
        status 200
        body([
                price: "90.00",
                factors: [

                ]
        ])
    }
}

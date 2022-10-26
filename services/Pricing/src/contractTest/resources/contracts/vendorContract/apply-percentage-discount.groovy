package contracts.vendorCMS

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description """
        Request to apply discounting percentage factor to a concert price
        """

    name "vendor discount request"

    request {

        method POST()

        url regex('http://localhost:9091/api/v1/item/' + uuid() + '/price-factor/percentage')

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
        headers {
            contentType applicationJson()
        }
        body([
                price  : 90_00,
                basePrice: 100_00,
                factors: [
                        [
                                "name"  : "PERCENTAGE",
                                "params": [
                                        "value": "10",
                                        "type" : "MINUS"
                                ]
                        ]

                ]
        ])
    }
}

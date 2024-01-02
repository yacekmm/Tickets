package contracts.promoter.percentageDiscount

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description """
        Request to apply discounting percentage factor to a concert price
        """

    name "promoter discount request"

    request {

        method POST()

        url '/api/v1/item/00000000-0000-0000-0000-000000000000/price-factor/percentage'

        headers {
            contentType applicationJson()
            accept applicationJson()
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
        body(
                [[
                      priceId: regex(uuid()),
                      price  : 90_00,
                      factors: [[
                                        type : "PERCENTAGE",
                                        value: 10
                                ]
                      ]
              ]]
        )
    }
}

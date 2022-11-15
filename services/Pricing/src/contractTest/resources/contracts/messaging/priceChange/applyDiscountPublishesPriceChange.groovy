package contracts.messaging.priceChange

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	label("trigger")
	input {
		triggeredBy('applyPercentageDiscount(10)')
	}
	outputMessage {
		sentTo("pricing.price")
		body([
		        id: regex(uuid()),
				version: 'v1',
				type: 'PRICE_CHANGE',
				payload: [
				        priceId: regex(uuid()),
						itemId: regex(uuid()),
						price: 90_00
				]

		])
	}
}
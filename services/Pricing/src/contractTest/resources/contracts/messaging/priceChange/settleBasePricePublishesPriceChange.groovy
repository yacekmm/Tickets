package contracts.messaging.priceChange

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	label("trigger")
	input {
		triggeredBy('settleInitialPrice()')
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
						price: 105_00
				]

		])
	}
}
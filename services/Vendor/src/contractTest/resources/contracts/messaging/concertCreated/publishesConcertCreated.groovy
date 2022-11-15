package contracts.messaging.concertCreated

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	label("triggerConcertCreatedEvent")
	input {
		triggeredBy('createConcert("mock-title", "2042-03-07", "mock-vendor-id")')
	}
	outputMessage {
		sentTo("vendor.concert")
		body([
		        id: regex(uuid()),
				version: 'v1',
				type: 'CONCERT_CREATED',
				payload: [
						serialization_type: 'CONCERT_CREATED',
				        concertId: regex(uuid()),
						title: 'mock-title',
						date: '2042-03-07',
						tags: [],
						profitMarginPercentage: 5
				]

		])
	}
}
package contracts.messaging.concertCreated

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	label("triggerConcertCreatedEvent")
	input {
		triggeredBy('createConcert("mock-title", "2042-03-07", "mock-promoter-id")')
	}
	outputMessage {
		sentTo("promoter.concert")
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
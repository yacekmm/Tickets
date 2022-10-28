package contracts.messaging.concertCreated

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	label("trigger")
	input {
		triggeredBy('createConcert("mock-title", TEST_TIME_PLUS_30_DAYS, "mock-vendor-id")')
	}
	outputMessage {
		sentTo("vendor.concert")
		body([
		        id: regex(uuid()),
				version: 'v1',
				type: 'CONCERT_CREATED',
				payload: [
				        concertId: regex(uuid()),
						title: 'mock-title',
						dateTime: '2022-03-07T07:20:00Z',
						tags: [[]],
						profitMarginPercentage: 0
				]

		])
	}
}
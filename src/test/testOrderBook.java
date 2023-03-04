import OrderBook.Order;
import OrderBook.Fill;
import OrderBook.OrderBook;
import OrderBook.Side;

import static OrderBook.OrderType.MARKET_ORDER;
import static OrderBook.OrderType.LIMIT_ORDER;
import static OrderBook.OrderType.STOP_LIMIT_ORDER;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class testOrderBook {

	@Test
	void placeOneAsk() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Operation
		Order newAsk = new Order(10.2f, 1.5f, Side.ASK, 1677539237L);
		testSubject.placeOrder(newAsk);
		// Checking Operation
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(10.2f, testSubject.asks.getFirst().price);
		assertEquals(1.5f, testSubject.asks.getFirst().size);
		assertEquals(1677539237L, testSubject.asks.getFirst().createdTs);
		assertEquals(10.2f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice); // Should be unaffected
		assertEquals(1.5f, testSubject.sittingVolume);
	}

	@Test
	void placeOneAskTwo() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		//Operation
		Order newAsk = new Order(5.3f, 0.3f, Side.ASK, 1677599362L);
		testSubject.placeOrder(newAsk);
		//Checking Operation
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(5.3f, testSubject.asks.getFirst().price);
		assertEquals(0.3f, testSubject.asks.getFirst().size);
		assertEquals(1677599362L, testSubject.asks.getFirst().createdTs);
		assertEquals(5.3f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0.3f, testSubject.sittingVolume);
	}

	@Test
	void placeOneBid() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Operation
		Order newAsk = new Order(3.39f, 0.25f, Side.BID, 1677541289L);
		testSubject.placeOrder(newAsk);
		// Checking Operation
		assertEquals(0, testSubject.asks.size());
		assertEquals(1, testSubject.bids.size());
		assertEquals(3.39f, testSubject.bids.getFirst().price);
		assertEquals(0.25f, testSubject.bids.getFirst().size);
		assertEquals(1677541289L, testSubject.bids.getFirst().createdTs);
		assertEquals(-1f, testSubject.bestAskPrice); // Should be unaffected
		assertEquals(3.39f, testSubject.bestBidPrice);
		assertEquals(0.25f, testSubject.sittingVolume);
	}

	@Test
	void placeOneBidTwo() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		//Operation
		Order newBid = new Order(5.3f, 0.3f, Side.BID, 1677598183L);
		testSubject.placeOrder(newBid);
		//Checking Operation
		assertEquals(0, testSubject.asks.size());
		assertEquals(1, testSubject.bids.size());
		assertEquals(5.3f, testSubject.bids.getFirst().price);
		assertEquals(0.3f, testSubject.bids.getFirst().size);
		assertEquals(1677598183L, testSubject.bids.getFirst().createdTs);
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(5.3f, testSubject.bestBidPrice);
		assertEquals(0.3f, testSubject.sittingVolume);
	}

	@Test
	void placeOneAskAndBid() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Operation
		Order newAsk = new Order(10.2f, 1.5f, Side.ASK, 1677539237L);
		testSubject.placeOrder(newAsk);
		// Checking Operation
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(10.2f, testSubject.asks.getFirst().price);
		assertEquals(1.5f, testSubject.asks.getFirst().size);
		assertEquals(1677539237L, testSubject.asks.getFirst().createdTs);
		assertEquals(10.2f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice); // Should be unaffected
		assertEquals(1.5f, testSubject.sittingVolume);
		//Operation
		Order newBid = new Order(5.3f, 0.3f, Side.BID, 1677598183L);
		testSubject.placeOrder(newBid);
		//Checking Operation
		assertEquals(1, testSubject.asks.size());
		assertEquals(1, testSubject.bids.size());
		assertEquals(5.3f, testSubject.bids.getFirst().price);
		assertEquals(0.3f, testSubject.bids.getFirst().size);
		assertEquals(1677598183L, testSubject.bids.getFirst().createdTs);
		assertEquals(10.2f, testSubject.bestAskPrice);
		assertEquals(5.3f, testSubject.bestBidPrice);
		assertEquals(1.8f, testSubject.sittingVolume);
	}

	@Test
	void placeTwoAsk() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Operation
		Order newAsk = new Order(10.2f, 1.5f, Side.ASK, 1677539237L);
		testSubject.placeOrder(newAsk);
		// Checking Operation
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(10.2f, testSubject.asks.getFirst().price);
		assertEquals(1.5f, testSubject.asks.getFirst().size);
		assertEquals(1677539237L, testSubject.asks.getFirst().createdTs);
		assertEquals(10.2f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice); // Should be unaffected
		assertEquals(1.5f, testSubject.sittingVolume);
		//Operation
		Order newAskOne = new Order(5.3f, 0.3f, Side.ASK, 1677598183L);
		testSubject.placeOrder(newAskOne);
		//Checking Operation
		assertEquals(2, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(5.3f, testSubject.asks.getFirst().price);
		assertEquals(0.3f, testSubject.asks.getFirst().size);
		assertEquals(1677598183L, testSubject.asks.getFirst().createdTs);
		assertEquals(5.3f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(1.8f, testSubject.sittingVolume);
	}

	@Test
	void placeOneBidSTOPLIMIT() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Operation
		Order newSTOPLIMIT = new Order("helloUUID!", "helloproductID!", 2.2f, 99.99f,
				STOP_LIMIT_ORDER, false, Side.BID, 1677598183L);
		testSubject.placeOrder(newSTOPLIMIT);
		// Checking Operation
		assertEquals(0, testSubject.asks.size());
		assertEquals(1, testSubject.bids.size());
		assertEquals(99.99f, testSubject.bids.getFirst().price);
		assertEquals(2.2f, testSubject.bids.getFirst().size);
		assertEquals(1677598183L, testSubject.bids.getFirst().createdTs);
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(99.99f, testSubject.bestBidPrice);
		assertEquals(2.2f, testSubject.sittingVolume);
	}

	@Test
	void placeOneAskLIMITORDER() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Operation
		Order newLIMIT = new Order("helloUUID!", "helloproductID!", 6.9f, 69.69f,
				LIMIT_ORDER, false, Side.ASK, 69L);
		testSubject.placeOrder(newLIMIT);
		// Checking Operation
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(69.69f, testSubject.asks.getFirst().price);
		assertEquals(6.9f, testSubject.asks.getFirst().size);
		assertEquals(69L, testSubject.asks.getFirst().createdTs);
		assertEquals(69.69f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(6.9f, testSubject.sittingVolume);
	}

	@Test
	void placeTwoAskMARKETORDERandOneBidLIMITORDERthrowCROSSED_ORDERBOOK() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Operation
		Order newMARKETORDER = new Order("helloUUID!", "helloproductID_1!", 106.0f,
				99.99f, MARKET_ORDER, false, Side.ASK, 1677598097L);
		testSubject.placeOrder(newMARKETORDER);
		// Checking Operation
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(99.99f, testSubject.asks.getFirst().price); // No orders have been filled yet
		assertEquals(106.0f, testSubject.asks.getFirst().size);	 // No orders have been filled yet
		assertEquals(1677598097L, testSubject.asks.getFirst().createdTs);
		assertEquals(99.99f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(106.0f, testSubject.sittingVolume);
		// Operation
		newMARKETORDER = new Order("helloUUID!", "helloproductID_2!", 3.7f,
				0.01f, MARKET_ORDER, false, Side.ASK, 1677598150L);
		testSubject.placeOrder(newMARKETORDER);
		// Checking Operation
		assertEquals(2, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(0.01f, testSubject.asks.getFirst().price);
		assertEquals(3.7f, testSubject.asks.getFirst().size);
		assertEquals(1677598150L, testSubject.asks.getFirst().createdTs);
		assertEquals(0.01f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(109.7f, testSubject.sittingVolume);
		/* // Operation
		Order newLIMITORDER = new Order("helloUUID!", "helloproductID_3!", 100.0f,
				49.97f, LIMIT_ORDER, false, Side.BID, 1677598167L);
		testSubject.placeOrder(newLIMITORDER);
		// Checking Operation, note CROSSED ORDERBOOK */
	}

	@Test
	void placeOneBidandOneAskLIMITORDERfillEvent() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Operation
		Order newLIMITORDER = new Order("helloUUID!", "helloproductID_1!", 100.0f,
				27.37f, LIMIT_ORDER, false, Side.BID, 100L);
		testSubject.placeOrder(newLIMITORDER);
		assertEquals(0, testSubject.asks.size());
		assertEquals(1, testSubject.bids.size());
		assertEquals(27.37f, testSubject.bids.getFirst().price);
		assertEquals(100.0f, testSubject.bids.getFirst().size);
		assertEquals(100L, testSubject.bids.getFirst().createdTs);
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(27.37f, testSubject.bestBidPrice);
		assertEquals(100.0f, testSubject.sittingVolume);
		// Operation
		newLIMITORDER = new Order("helloUUID!", "helloproductID_2!", 50.0f,
				34.31f, LIMIT_ORDER, false, Side.ASK, 200L);
		testSubject.placeOrder(newLIMITORDER);
		assertEquals(1, testSubject.asks.size());
		assertEquals(1, testSubject.bids.size());
		assertEquals(34.31f, testSubject.asks.getFirst().price);
		assertEquals(50.0f, testSubject.asks.getFirst().size);
		assertEquals(27.37f, testSubject.bids.getFirst().price);
		assertEquals(100.0f, testSubject.bids.getFirst().size);
		assertEquals(200L, testSubject.asks.getFirst().createdTs);
		assertEquals(100L, testSubject.bids.getFirst().createdTs);
		assertEquals(34.31f, testSubject.bestAskPrice);
		assertEquals(27.37f, testSubject.bestBidPrice);
		assertEquals(150.0f, testSubject.sittingVolume);
		// Operation
		Fill newFill = new Fill("helloproductID_2!", "helloclientID!", 35.5f,
				34.31f, 1.5f, LIMIT_ORDER, Side.ASK, 330L);
		testSubject.fillEvent(newFill);
		assertEquals(1, testSubject.asks.size());
		assertEquals(1, testSubject.bids.size());
		assertEquals(34.31f, testSubject.asks.getFirst().price);
		assertEquals(14.5f, testSubject.asks.getFirst().size);
		assertEquals(27.37f, testSubject.bids.getFirst().price);
		assertEquals(100.0f, testSubject.bids.getFirst().size);
		assertEquals(100L, testSubject.bids.getFirst().createdTs);
		assertEquals(34.31f, testSubject.bestAskPrice);
		assertEquals(27.37f, testSubject.bestBidPrice);
		assertEquals(114.5f, testSubject.sittingVolume);
	}
}
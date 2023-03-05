import OrderBook.Order;
import OrderBook.OrderBook;
import OrderBook.Side;
import org.junit.jupiter.api.Test;

import static OrderBook.OrderType.MARKET_ORDER;
import static OrderBook.OrderType.LIMIT_ORDER;
import static OrderBook.OrderType.STOP_LIMIT_ORDER;

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
	void placeThreeBids() {
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
		//Operation
		Order newBid2 = new Order(8.1f, 1.1f, Side.BID, 1677598184L);
		testSubject.placeOrder(newBid2);
		//Checking Operation
		assertEquals(0, testSubject.asks.size());
		assertEquals(2, testSubject.bids.size());
		assertEquals(5.3f, testSubject.bids.getFirst().price);
		assertEquals(0.3f, testSubject.bids.getFirst().size);
		assertEquals(1677598183L, testSubject.bids.getFirst().createdTs);
		assertEquals(8.1f, testSubject.bids.getLast().price);
		assertEquals(1.1f, testSubject.bids.getLast().size);
		assertEquals(1677598182L, testSubject.bids.getLast().createdTs);
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(8.1f, testSubject.bestBidPrice);
		assertEquals(1.4f, testSubject.sittingVolume);
		//Operation
		Order newBid3 = new Order(2.0f, 1.0f, Side.BID, 1677598185L);
		testSubject.placeOrder(newBid3);
		//Checking Operation
		assertEquals(0, testSubject.asks.size());
		assertEquals(3, testSubject.bids.size());
		assertEquals(5.3f, testSubject.bids.getFirst().price);
		assertEquals(0.3f, testSubject.bids.getFirst().size);
		assertEquals(1677598183L, testSubject.bids.getFirst().createdTs);
		assertEquals(2.0f, testSubject.bids.getLast().price);
		assertEquals(1.0f, testSubject.bids.getLast().size);
		assertEquals(1677598185L, testSubject.bids.getLast().createdTs);
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(8.1f, testSubject.bestBidPrice);
		assertEquals(2.4f, testSubject.sittingVolume);
	}

	@Test
	void placeOneLimitOrderAsk() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Operation
		Order newLIMIT = new Order("uuid", "product", 1.0f, 5.0f,
				LIMIT_ORDER, false, Side.ASK, 1677598186L);
		testSubject.placeOrder(newLIMIT);
		// Checking Operation
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(1.0f, testSubject.asks.getFirst().size);
		assertEquals(5.0f, testSubject.asks.getFirst().price);
		assertEquals(1677598186L, testSubject.asks.getFirst().createdTs);
		assertEquals(1.0f, testSubject.asks.getLast().size);
		assertEquals(5.0f, testSubject.asks.getLast().price);
		assertEquals(1677598186L, testSubject.asks.getLast().createdTs);
		assertEquals(5.0f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(1.0f, testSubject.sittingVolume);
	}

	@Test
	void placeOneLimitOrderAskOneMarketOrderAsk() {
		// Init and checking start conditions
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Operation
		Order newLimit = new Order("uuid", "product", 1.0f, 5.0f,
				LIMIT_ORDER, false, Side.ASK, 1677598186L);
		testSubject.placeOrder(newLimit);
		// Checking Operation
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(1.0f, testSubject.asks.getFirst().size);
		assertEquals(5.0f, testSubject.asks.getFirst().price);
		assertEquals(1677598186L, testSubject.asks.getFirst().createdTs);
		assertEquals(1.0f, testSubject.asks.getFirst().size);
		assertEquals(5.0f, testSubject.asks.getFirst().price);
		assertEquals(1677598186L, testSubject.asks.getFirst().createdTs);
		assertEquals(5.0f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(1.0f, testSubject.sittingVolume);
		// Operation
		Order newMarket = new Order("uuid", "product", 2.0f, 10.0f,
				MARKET_ORDER, false, Side.ASK, 1677598187L);
		testSubject.placeOrder(newMarket);
		// Checking Operation
		assertEquals(2, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(1.0f, testSubject.asks.getFirst().size);
		assertEquals(5.0f, testSubject.asks.getFirst().price);
		assertEquals(1677598186L, testSubject.asks.getFirst().createdTs);
		assertEquals(2.0f, testSubject.asks.getLast().size);
		assertEquals(10.0f, testSubject.asks.getLast().price);
		assertEquals(1677598187L, testSubject.asks.getLast().createdTs);
		assertEquals(10.0f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(3.0f, testSubject.sittingVolume);
	}

	@Test
	void placeTwoStopLimitBids(){
		OrderBook testSubject = new OrderBook();
		//Operation
		Order newStopLimit = new Order("uuid", "product", 3.0f, 5.0f,
				STOP_LIMIT_ORDER, false, Side.BID, 1677598188L);
		testSubject.placeOrder(newStopLimit);
		// Checking Operation
		assertEquals(0, testSubject.asks.size());
		assertEquals(1, testSubject.bids.size());
		assertEquals(3.0f, testSubject.asks.getFirst().size);
		assertEquals(5.0f, testSubject.asks.getFirst().price);
		assertEquals(1677598188L, testSubject.asks.getFirst().createdTs);
		assertEquals(3.0f, testSubject.asks.getLast().size);
		assertEquals(5.0f, testSubject.asks.getLast().price);
		assertEquals(1677598188L, testSubject.asks.getLast().createdTs);
		assertEquals(5.0f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(3.0f, testSubject.sittingVolume);
		//Operation
		Order newStopLimit2 = new Order("uuid", "product", 4.0f, 9.0f,
				STOP_LIMIT_ORDER, false, Side.BID, 1677598189L);
		testSubject.placeOrder(newStopLimit2);
		// Checking Operation
		assertEquals(0, testSubject.asks.size());
		assertEquals(2, testSubject.bids.size());
		assertEquals(3.0f, testSubject.asks.getFirst().size);
		assertEquals(5.0f, testSubject.asks.getFirst().price);
		assertEquals(1677598188L, testSubject.asks.getFirst().createdTs);
		assertEquals(4.0f, testSubject.asks.getLast().size);
		assertEquals(9.0f, testSubject.asks.getLast().price);
		assertEquals(1677598189L, testSubject.asks.getLast().createdTs);
		assertEquals(9.0f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(7.0f, testSubject.sittingVolume);
	}
}

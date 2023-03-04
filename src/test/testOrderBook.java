import OrderBook.Order;
import OrderBook.OrderBook;
import OrderBook.Side;
import org.junit.jupiter.api.Test;
import OrderBook.Fill;
import OrderBook.OrderType;

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
	void checkBest(){
		OrderBook testSubject = new OrderBook();

		//Bids:
		Order newBidOne = new Order(5.3f, 0.3f, Side.BID, 1677598183L);
		testSubject.placeOrder(newBidOne);
		Order newBidTwo = new Order(8.3f, 0.7f, Side.BID, 1977598183L);
		testSubject.placeOrder(newBidTwo);
		assertEquals(5.0, (double)Math.round(testSubject.bestBidPrice));

		//Asks:
		Order newAskOne = new Order(5.3f, 0.4f, Side.ASK, 1777598183L);
		testSubject.placeOrder(newAskOne);
		Order newAskTwo = new Order(8.3f, 0.2f, Side.ASK, 1877598183L);
		testSubject.placeOrder(newAskTwo);
		assertEquals(5.0, (double)Math.round(testSubject.bestAskPrice));

		assertEquals(0.0,(double)Math.round(testSubject.sittingVolume));

	}
}

import OrderBook.Order;
import OrderBook.OrderBook;
import OrderBook.Side;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class testOrderBook {

	@Test
	void placeOneAsk() {
		// Check default cond
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Append singular ask
		Order newAsk = new Order(30f, 3f, Side.ASK, 0L);
		testSubject.placeOrder(newAsk);
		// Verify modified values
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(30f, testSubject.asks.getFirst().price);
		assertEquals(3f, testSubject.asks.getFirst().size);
		assertEquals(0L, testSubject.asks.getFirst().createdTs);
		assertEquals(30f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice); // Should be unaffected
		assertEquals(3f, testSubject.sittingVolume);
	}

	@Test
	void placeTwoAsk() {
		// Init default OrderBook (no reason to check default cond)
		OrderBook testSubject = new OrderBook();
		// Append (first) ask
		Order newAsk = new Order(30f, 3f, Side.ASK, 0L);
		testSubject.placeOrder(newAsk);
		// Verify modified values
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(30f, testSubject.asks.getFirst().price);
		assertEquals(3f, testSubject.asks.getFirst().size);
		assertEquals(0L, testSubject.asks.getFirst().createdTs);
		assertEquals(30f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice); // Should be unaffected
		assertEquals(3f, testSubject.sittingVolume);
		// Append (second) ask
		Order newAsk2 = new Order(92.f, 2.f, Side.ASK, 1L);
		testSubject.placeOrder(newAsk2);
		// Verify modified values
		assertEquals(2, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(30f, testSubject.asks.getFirst().price);
		assertEquals(3f, testSubject.asks.getFirst().size);
		assertEquals(0L, testSubject.asks.getFirst().createdTs);
		assertEquals(30f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice); // Should be unaffected
		assertEquals(5f, testSubject.sittingVolume);
	}

	@Test
	void placeOneBid() {
		// Init default OrderBook (no reason to check default cond)
		OrderBook testSubject = new OrderBook();
		// Append singular bid
		Order newBid = new Order(5f, 0.25f, Side.BID, 0L);
		testSubject.placeOrder(newBid);
		// Verify modified values
		assertEquals(0, testSubject.asks.size());
		assertEquals(1, testSubject.bids.size());
		assertEquals(5f, testSubject.bids.getFirst().price);
		assertEquals(0.25f, testSubject.bids.getFirst().size);
		assertEquals(0L, testSubject.bids.getFirst().createdTs);
		assertEquals(-1f, testSubject.bestAskPrice); // Should be unaffected
		assertEquals(5f, testSubject.bestBidPrice);
		assertEquals(0.25f, testSubject.sittingVolume);
	}

	@Test
	void placeOneAskAndBid() {
		// Init default OrderBook (no reason to check default cond)
		OrderBook testSubject = new OrderBook();
		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);
		// Append singular ask
		Order newAsk = new Order(5f, 1.5f, Side.ASK, 0L);
		testSubject.placeOrder(newAsk);
		// Verify modified values
		assertEquals(1, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(5f, testSubject.asks.getFirst().price);
		assertEquals(1.5f, testSubject.asks.getFirst().size);
		assertEquals(0L, testSubject.asks.getFirst().createdTs);
		assertEquals(5f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice); // Should be unaffected
		assertEquals(1.5f, testSubject.sittingVolume);
		// Append singular bid
		Order newBid = new Order(92f, 0.25f, Side.BID, 10L);
		testSubject.placeOrder(newBid);
		// Verify modified values
		assertEquals(1, testSubject.asks.size());
		assertEquals(1, testSubject.bids.size());
		assertEquals(92f, testSubject.bids.getFirst().price);
		assertEquals(0.25f, testSubject.bids.getFirst().size);
		assertEquals(10L, testSubject.bids.getFirst().createdTs);
		assertEquals(5f, testSubject.bestAskPrice);
		assertEquals(92f, testSubject.bestBidPrice);
		assertEquals(1.75f, testSubject.sittingVolume);
	}
}

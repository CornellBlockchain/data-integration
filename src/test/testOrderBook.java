import OrderBook.Order;
import OrderBook.OrderBook;
import OrderBook.Side;
import OrderBook.Fill;
import OrderBook.OrderType;
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

		// Init
		OrderBook testSubject2 = new OrderBook();

		// Operation
		Order newAsk2 = new Order(5.01f, 2.0f, Side.ASK, 1010101010L);
		testSubject2.placeOrder(newAsk2);
		// Checking Operation
		assertEquals(1, testSubject2.asks.size());
		assertEquals(0, testSubject2.bids.size());
		assertEquals(5.01f, testSubject2.asks.getFirst().price);
		assertEquals(2.0f, testSubject2.asks.getFirst().size);
		assertEquals(1010101010L, testSubject2.asks.getFirst().createdTs);
		assertEquals(5.01f, testSubject2.bestAskPrice);
		assertEquals(-1f, testSubject2.bestBidPrice); // Should be unaffected
		assertEquals(2.0f, testSubject2.sittingVolume);
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
		assertEquals(1677599362L, testSubject.bids.getFirst().createdTs);
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

		// Init and checking start conditions
		OrderBook testSubject2 = new OrderBook();

		// Operation
		Order newBid2 = new Order(6.519f, 0.75f, Side.BID, 11110011L);
		testSubject2.placeOrder(newBid2);
		// Checking Operation
		assertEquals(0, testSubject2.asks.size());
		assertEquals(1, testSubject2.bids.size());
		assertEquals(6.519f, testSubject2.bids.getFirst().price);
		assertEquals(0.75f, testSubject2.bids.getFirst().size);
		assertEquals(11110011L, testSubject2.bids.getFirst().createdTs);
		assertEquals(-1f, testSubject2.bestAskPrice); // Should be unaffected
		assertEquals(6.519f, testSubject2.bestBidPrice);
		assertEquals(0.75f, testSubject2.sittingVolume);
	}

	@Test
	void placeMultipleOrders() {
		// Init
		OrderBook testSubject = new OrderBook();

		// Operation
		Order newBid = new Order("123","bid1", 4.39f, 0.25f, OrderType.MARKET_ORDER, true,
				Side.BID, 1677541289L);
		testSubject.placeOrder(newBid);

		Order newBid2 = new Order("23","bid2", 5.01f, 0.3f, OrderType.MARKET_ORDER, true,
				Side.BID, 1677541212L);
		testSubject.placeOrder(newBid2);

		Order newAsk = new Order("32","ask1", 2.25f, 0.4f, OrderType.MARKET_ORDER, true,
				Side.ASK, 167754132L);
		testSubject.placeOrder(newAsk);

		Order newAsk2 = new Order("3","ask2", 1.25f, 0.35f, OrderType.MARKET_ORDER, true,
				Side.ASK, 167754132L);
		testSubject.placeOrder(newAsk2);

		// Checking Operation
		assertEquals(2, testSubject.bids.size());
		assertEquals(2, testSubject.asks.size());
		assertEquals(0.3f, testSubject.bids.getFirst().price);
		assertEquals(0.4f, testSubject.bestAskPrice);
		assertEquals(0.3f, testSubject.bestBidPrice);
		assertEquals(12.9f, testSubject.sittingVolume);
	}

	@Test
	void OneFillEvent() {
		// Init
		OrderBook testSubject = new OrderBook();

		// Operation
		Order newBid = new Order("123","bid1", 4.39f, 0.25f, OrderType.MARKET_ORDER, true,
				Side.BID, 1677541289L);
		testSubject.placeOrder(newBid);


		assertEquals(4.39f, testSubject.sittingVolume);

		Fill fill = new Fill("12", "hi", 4.39f, 0.25f, 0.01f, OrderType.MARKET_ORDER,  Side.BID, 1677541289L);
		testSubject.fillEvent(fill);

		assertEquals(0.0f, testSubject.sittingVolume);
		// Checking Operation
		assertEquals(0, testSubject.bids.size());
		assertEquals(0, testSubject.asks.size());

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
		assertEquals(5.3f, testSubject.asks.getLast().price);
		assertEquals(0.3f, testSubject.asks.getLast().size);
		assertEquals(1677598183L, testSubject.bids.getFirst().createdTs);
		assertEquals(10.2f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(1.8f, testSubject.sittingVolume);
	}
}

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
	void placeMultipleOrders() {
		// Init
		OrderBook testSubject = new OrderBook();

		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);

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
		assertEquals(0.35f, testSubject.bestAskPrice);
		assertEquals(0.3f, testSubject.bestBidPrice);
		assertEquals(12.9f, testSubject.sittingVolume);
	}

	@Test
	void OneFillEvent() {
		// Init
		OrderBook testSubject = new OrderBook();

		assertEquals(0, testSubject.asks.size());
		assertEquals(0, testSubject.bids.size());
		assertEquals(-1f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(0f, testSubject.sittingVolume);

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
		assertEquals(5.3f, testSubject.asks.getFirst().price);
		assertEquals(0.3f, testSubject.asks.getFirst().size);
		assertEquals(1677598183L, testSubject.asks.getFirst().createdTs);
		assertEquals(5.3f, testSubject.bestAskPrice);
		assertEquals(-1f, testSubject.bestBidPrice);
		assertEquals(1.8f, testSubject.sittingVolume);
	}
	@Test
	void placeSimpleAsk(){
		OrderBook obTest = new OrderBook();
		assertEquals(0, obTest.asks.size());
		assertEquals(0, obTest.bids.size());
		assertEquals(-1f, obTest.bestAskPrice);
		assertEquals(-1f, obTest.bestBidPrice);
		assertEquals(0f, obTest.sittingVolume);

		Order newAsk = new Order(5.1f, 7.2f, Side.ASK, 2347698784L);
		obTest.placeOrder(newAsk);

		assertEquals(1, obTest.asks.size());
		assertEquals(0, obTest.bids.size());
		assertEquals(5.1f, obTest.asks.getFirst().price);
		assertEquals(7.2f, obTest.asks.getFirst().size);
		assertEquals(2347698784L, obTest.asks.getFirst().createdTs);
		assertEquals(5.1f, obTest.bestAskPrice);
		assertEquals(-1f, obTest.bestBidPrice); // Should be unaffected
		assertEquals(7.2f, obTest.sittingVolume);
	}

	@Test
	void placeSimpleBid(){
		OrderBook obTest = new OrderBook();
		assertEquals(0, obTest.asks.size());
		assertEquals(0, obTest.bids.size());
		assertEquals(-1f, obTest.bestAskPrice);
		assertEquals(-1f, obTest.bestBidPrice);
		assertEquals(0f, obTest.sittingVolume);
		// Operation
		Order newBid = new Order(6.3f, 5.8f, Side.BID, 3277777780L);
		obTest.placeOrder(newBid);
		// Checking Operation
		assertEquals(0, obTest.asks.size());
		assertEquals(1, obTest.bids.size());
		assertEquals(6.3f, obTest.bids.getFirst().price);
		assertEquals(5.8f, obTest.bids.getFirst().size);
		assertEquals(3277777780L, obTest.bids.getFirst().createdTs);
		assertEquals(-1f, obTest.bestAskPrice); // Should be unaffected
		assertEquals(6.3f, obTest.bestBidPrice);
		assertEquals(5.8f, obTest.sittingVolume);
	}

	@Test
	void multipleOrdersPlusFill(){
		OrderBook obTest = new OrderBook();
		assertEquals(0, obTest.asks.size());
		assertEquals(0, obTest.bids.size());
		assertEquals(-1f, obTest.bestAskPrice);
		assertEquals(-1f, obTest.bestBidPrice);
		assertEquals(0f, obTest.sittingVolume);

		Order newBid1 = new Order("111","bid1", 2.5f, 10.0f, OrderType.MARKET_ORDER, true,
				Side.BID, 3277777777L);
		obTest.placeOrder(newBid1);

		Order newBid2 = new Order("112","bid2", 7.5f, 90.0f, OrderType.MARKET_ORDER, true,
				Side.BID, 3277779955L);
		obTest.placeOrder(newBid2);

		Order newAsk1 = new Order("11","ask1", 1.0f, 92.4f, OrderType.MARKET_ORDER, true,
				Side.ASK, 2277779955L);
		obTest.placeOrder(newAsk1);

		assertEquals(2, obTest.bids.size());
		assertEquals(1, obTest.asks.size());
		assertEquals(90.0f, obTest.bids.getFirst().price);
		assertEquals(92.4f, obTest.bestAskPrice);
		assertEquals(90.0f, obTest.bestBidPrice);
		assertEquals(11.0f, obTest.sittingVolume);

		Fill fill1 = new Fill("1", "him", 7.5f, 90.0f, 0.01f, OrderType.MARKET_ORDER,  Side.BID, 3277779955L);
		obTest.fillEvent(fill1);

		assertEquals(3.5f, obTest.sittingVolume);
		assertEquals(1, obTest.bids.size());
		assertEquals(1, obTest.asks.size());

		Fill fill2 = new Fill("12", "him2", 2.5f, 10.0f, 0.02f, OrderType.MARKET_ORDER,  Side.BID, 3277777777L);
		obTest.fillEvent(fill2);

		assertEquals(1.0f, obTest.sittingVolume);
		assertEquals(0, obTest.bids.size());
		assertEquals(1, obTest.asks.size());
	}

	@Test
	void RedundantFill(){
		OrderBook obTest = new OrderBook();
		assertEquals(0, obTest.asks.size());
		assertEquals(0, obTest.bids.size());
		assertEquals(-1f, obTest.bestAskPrice);
		assertEquals(-1f, obTest.bestBidPrice);
		assertEquals(0f, obTest.sittingVolume);
		// Operation
		Order newBid1 = new Order(6.3f, 5.8f, Side.BID, 3277777780L);
		obTest.placeOrder(newBid1);

		Order newBid2 = new Order("112","bid2", 7.0f, 90.0f, OrderType.MARKET_ORDER, true,
				Side.BID, 3277779955L);
		obTest.placeOrder(newBid2);

		assertEquals(12.8f, obTest.sittingVolume);

		Fill fill1 = new Fill("1", "him", 5.8f, 6.3f, 0.01f, OrderType.MARKET_ORDER,  Side.BID, 3277777780L);
		obTest.fillEvent(fill1);

		assertEquals(7.0f, obTest.sittingVolume);
		assertEquals(1, obTest.bids.size());
		assertEquals(0, obTest.asks.size());

		obTest.fillEvent(fill1);
		assertEquals(7.0f, obTest.sittingVolume);
		assertEquals(1, obTest.bids.size());
		assertEquals(0, obTest.asks.size());
	}
}

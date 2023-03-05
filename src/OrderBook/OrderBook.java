package OrderBook;

import java.time.ZonedDateTime;
import java.util.*;

public class OrderBook {
	public LinkedList<Order> asks = new LinkedList<>();
	public LinkedList<Order> bids = new LinkedList<>();

	Cache<String, String> placedOrders = new Cache<String, String>(20000);
	Cache<String, String> recordedFills = new Cache<String, String>(20000);

	public float bestBidPrice = -1; // Negative implies no bid/ask at all
	public float bestAskPrice = -1;
	public float sittingVolume = 0;

	// Helpers
	private void sortedPlace(Order order){
		boolean isAsk = order.side==Side.ASK;
		LinkedList<Order> sideOfBook = isAsk ? asks : bids;
		for (int i = 0; i < sideOfBook.size(); i++) {
			if (sideOfBook.get(i).price == order.price){
				order.size += sideOfBook.get(i).size;
				// Keep new info for 'price level'
				if (order.side == Side.ASK)
					asks.set(i,order);
				else
					bids.set(i, order);
				return;
			}
			else if (sideOfBook.get(i).price < order.price && !isAsk ||
						sideOfBook.get(i).price > order.price && isAsk) {
				if (order.side == Side.ASK)
					asks.add(i,order);
				else
					bids.add(i, order);
				return;
			}
		}
		if (order.side == Side.ASK)
			asks.addLast(order);
		else
			bids.addLast(order);
	}

	private void sortedFill(Fill fill){
		LinkedList<Order> sideOfBook = fill.side==Side.ASK ? asks : bids;
		for (int i = 0; i < sideOfBook.size(); i++) {
			if (sideOfBook.get(i).price == fill.price){
				if (fill.size < sideOfBook.get(i).size) {
					Order newOrder = new Order(fill.price,
							sideOfBook.get(i).size - fill.size, fill.side,
							ZonedDateTime.now().toInstant().toEpochMilli());
					sideOfBook.set(i, newOrder);
				}
				else { // Fills entire price level
					if (fill.side == Side.ASK)
						asks.remove(i);
					else
						bids.remove(i);
				}
				// The exchanges might send fills before the order, we need
				// to address this
				return;
			}
		}
		throw new RuntimeException("OrderBook.Fill event didn't fill any sitting orders");
	}

	private void adjustBBO(){
		bestAskPrice = asks.size() > 0 ? asks.getFirst().price : -1;
		bestBidPrice = bids.size() > 0 ? bids.getFirst().price : -1;
	}

	/**
	 * placeOrder - Will place an order on the order book
	 * @param order A valid order
	 */
	public void placeOrder(Order order){
		if (order.orderID != null && placedOrders.containsKey(order.orderID))
			return; // Don't place an order we already placed
		// Insert OrderBook.Order into proper book
		sortedPlace(order);
		sittingVolume += order.size;
		adjustBBO();
		placedOrders.put(order.orderID, order.orderID);

		if (bestBidPrice > bestAskPrice && bestBidPrice != -1 && bestAskPrice != -1) {
			// NEW ASSERTION, check for crossed orderbook condition where best bid over best ask
			throw new RuntimeException("Crossed orderbook condition!");
		}
	}


	/**
	 * Registers fill event on the order book
	 * WARNING: Will throw if fill event doesn't fill any sitting order
	 * @param fill - Valid OrderBook.Fill that occured on the current version of the
	 *                order book
	 */
	public void fillEvent(Fill fill){
		if (recordedFills.containsKey(fill.orderID))
			return; // Don't place an order we already placed
		sortedFill(fill);
		sittingVolume -= fill.size;
		adjustBBO();
		recordedFills.put(fill.orderID, fill.orderID);
	}
}
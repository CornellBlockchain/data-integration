import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OrderBook {
	LinkedList<Order> asks;
	LinkedList<Order> bids;

	Cache<String, String> placedOrders = new Cache<>(20000);
	Cache<String, String> recordedFills = new Cache<>(20000);

	float bestBidPrice;
	float bestAskPrice;
	float sittingVolume;

	// Helpers
	private void sortedPlace(Order order){
		LinkedList<Order> sideOfBook = order.side==Side.ASK ? asks : bids;
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
			else if (sideOfBook.get(i).price < order.price){
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
					Order newOrder = sideOfBook.get(i);
					newOrder.size -= fill.size;
					sideOfBook.set(i, newOrder);
				}
				else { // Fills entire price level
					if (fill.side == Side.ASK)
						asks.remove(i);
					else
						bids.remove(i);
				}
				// What about slippage?
				return;
			}
		}
		throw new RuntimeException("Fill event didn't fill any sitting orders");
	}

	private void adjustBBO(){
		bestAskPrice = asks.getFirst().price;
		bestBidPrice = bids.getFirst().price;
	}

	/**
	 * placeOrder - Will place an order on the order book
	 * @param order A valid order
	 */
	public void placeOrder(@NotNull Order order){
		if (placedOrders.containsKey(order.orderID))
			return; // Don't place an order we already placed
		// Insert Order into proper book
		sortedPlace(order);
		sittingVolume += order.size;
		adjustBBO();
		placedOrders.put(order.orderID, order.orderID);
	}

	/**
	 * Registers fill event on the order book
	 * WARNING: Will throw if fill event doesn't fill any sitting order
	 * @param fill - Valid Fill that occured on the current version of the
	 *                order book
	 */
	public void fillEvent(@NotNull Fill fill){
		if (recordedFills.containsKey(fill.orderID))
			return; // Don't place an order we already placed
		sortedFill(fill);
		sittingVolume -= fill.size;
		adjustBBO();
		recordedFills.put(fill.orderID, fill.orderID);
	}
}

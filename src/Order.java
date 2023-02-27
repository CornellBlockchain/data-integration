public class Order {
	public String uuid; // Universal ID of the Order
	public String orderID; // The ID of the order set by the exchange
	public String clientID; // The ID of the order set by the one who created/placed the order

	public float size;
	public float price;
	public OrderType type;
	public TimeInForce timeInForce;
	public boolean postOnly; // Is this order a maker order? i.e. was this made by a market maker
	public Side side;

	public long createdTs; // Timestamp for when order was created

	// Chained Constructors
	public Order(String uuid, String productID, float size, float price,
	             OrderType type, boolean postOnly, Side side, long timestamp) {
		this.uuid = uuid;
		this.orderID = productID;
		this.size = size;
		this.price = price;
		this.type = type;
		this.postOnly = postOnly;
		this.side = side;
		this.createdTs = timestamp;
	}

	public Order(String uuid, String productID, float size, float price,
	             OrderType type, boolean postOnly, Side side, long timestamp,
	             String clientID) {
		this(uuid, productID, size, price, type, postOnly, side, timestamp);
		this.clientID = clientID;
	}

	public Order(String uuid, String productID, float size, float price,
	             OrderType type, boolean postOnly, Side side, long timestamp,
	             TimeInForce timeInForce) {
		this(uuid, productID, size, price, type, postOnly, side, timestamp);
		this.timeInForce = timeInForce;
	}

	public Order(String uuid, String productID, float size, float price,
	             OrderType type, boolean postOnly, Side side, long timestamp,
	             String clientID, TimeInForce timeInForce) {
		this(uuid, productID, size, price, type, postOnly, side, timestamp,
				timeInForce);
		this.clientID = clientID;
	}

}

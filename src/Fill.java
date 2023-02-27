public class Fill {
	public String orderID; // The ID of the order set by the exchange
	public String clientID; // The ID of the order set by the one who created/placed the order
	public String targetOrder; // The ID of the order being filled (if it exists)

	public float size;
	public float price;
	public float fee;
	public OrderType type;
	public Side side; // Which side it filled

	public long createdTs; // Timestamp for when order was created

	public Fill(String orderID, String clientID, float size, float price,
	            float fee, OrderType type, Side side, long timestamp){
		this.orderID = orderID;
		this.clientID = clientID;
		this.size = size;
		this.price = price;
		this.fee = fee;
		this.type = type;
		this.side = side;
		this.createdTs = timestamp;
	}
}

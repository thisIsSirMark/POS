package order;

public class Order {

	private Integer orderId, quantity, transactionId, productId;
	private Double subtotal;
	private Boolean isVisible;

	public Order() {

	}

	public Order(int orderId, int quantity, int transactionId, int productId, double subtotal) {
		super();
		this.orderId = orderId;
		this.quantity = quantity;
		this.transactionId = transactionId;
		this.productId = productId;
		this.subtotal = subtotal;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public int getProductId() {
		return productId;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", quantity=" + quantity + ", transactionId=" + transactionId
				+ ", productId=" + productId + ", subtotal=" + subtotal + "]";
	}

}

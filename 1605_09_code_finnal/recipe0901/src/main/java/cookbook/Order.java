package cookbook;

import java.io.Serializable;

public class Order implements Serializable {
	public Payment payment;

	public String toString() {
		return "Order [payment=" + payment + "]";
	}
}

package cookbook;

public class CreditCardPayment extends Payment {
	public String name;
	public String card;
	public String ccv;

	public String toString() {
		return "CreditCardPayment [name=" + name + ", card=" + card + ", ccv="
				+ ccv + "]";
	}

}

// CLASS: Stock
//
// Author: Sijin Lee, 7822352
//
// REMARKS: Implements a Stock used in StockMarket
//
//-----------------------------------------


public class Stock {
	private String name;
	private double price;
	private int balance; //balance = -1 indicates there is no notion of balance..


	//------------------------------------------------------
	// Stock()
	//
	// PURPOSE:  constructor for Stock Class
	// PARAMETERS:
	//    String, double, int
	// Returns: Stock object
	public Stock(String name, double price, int balance) {
		this.name = name;
		this.price = price;
		this.balance = balance;
	}

	//------------------------------------------------------
	// Stock()
	//
	// PURPOSE:  another constructor for Stock Class
	// PARAMETERS:
	//    Stock
	// Returns: Stock object
	public Stock(Stock stock) {
		this.name = stock.getName();
		this.price = stock.getPrice();
		this.balance = stock.getBalance();
	}

	//------------------------------------------------------
	// toString()
	//
	// PURPOSE: to override String representation of object
	// PARAMETERS:
	//   None
	// Returns: String representation of object
	@Override
	public String toString() {
		return balance + " of " + name + " at $" + price;
	}

	//------------------------------------------------------
	// getter methods
	//
	// PURPOSE:  get Name, Price, and Balance for corresponding object
	// PARAMETERS:
	//   None
	// Returns: string, double, int
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getBalance() {
		return balance;
	}


	//------------------------------------------------------
	// setter methods
	//
	// PURPOSE:  set Name, Price, and Balance for corresponding object
	// PARAMETERS:
	//   String, double, balance
	// Returns: None
	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}

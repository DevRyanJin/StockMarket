// CLASS: Order
//
// Author: Sijin Lee, 7822352
//
// REMARKS: Implements Order with corresponding stock, price, add or bid, and # of stock
//
//-----------------------------------------


public class Order
{
	private Stock stock;
	private double price; //ask or bid price
	private Account source; //ask or bid source
	private int quant; // the quantity of stock ask or bid


	//------------------------------------------------------
	// Order(Stock stock,double price,Account source,int quant)
	//
	// PURPOSE:  constructor for Order class
	// PARAMETERS:
	//    Stock, double, Account, int
	// Returns: initialize all corresponding instances of class
    public Order(Stock stock,double price,Account source,int quant)
    {
        this.stock = stock;
        //Not this.stock = new Stock(stock)..because after a particular transaction happens,
        //it should be able to change the price of this particular stock
        this.price = price;
        this.source = source; //Same reason..we need ref to source account
        this.quant = quant;
    }

    @Override
    public boolean equals(Object o)
    {
    	Order other = (Order)o;

    	if(stock.getName().equals(other.getStock().getName())
    			&& source.getAccountNo().equals(other.getSource().getAccountNo()))
    		return true;

    	return false;
    }

	//------------------------------------------------------
	// getter methods
	//
	// PURPOSE:  getStock, Price, Source, and Quantity
	// PARAMETERS:
	//   None
	// Returns: Stock, double, Account, int
	public Stock getStock() {
		return stock;
	}

	public double getPrice() {
		return price;
	}

	public Account getSource() {
		return source;
	}

	public int getQuant() {
		return quant;
	}

	//------------------------------------------------------
	// setter methods
	//
	// PURPOSE:  set Stock, Price, Account, and quantity
	// PARAMETERS:
	//  Stock, double, Account, int
	// Returns: None
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSource(Account source) {
		this.source = source;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}


}
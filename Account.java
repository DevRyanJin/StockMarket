// CLASS: Account
//
// Author: Sijin Lee, 7822352
//
// REMARKS: Implements a Account for the use of setting account
//
//-----------------------------------------

public class Account
{
    private String accountNo;
    private double balance;
    private Node stocks;


	//------------------------------------------------------
	// Account()
	//
	// PURPOSE:  constructor for Account Class
	// PARAMETERS:
	//    String, double
	// Returns: Account object
    public Account(String accNo,double balance)
    {
        this.accountNo = accNo;
        this.balance = balance;
        stocks = null;
    }

    //------------------------------------------------------
	// hasStock(String stockName)
	//
	// PURPOSE:  to check if the stockName is already in Account object list
	// PARAMETERS: to check if the stockName is already in Account object list
	//   String
	// Returns: Boolean
    public boolean hasStock(String stockName)
    {
    	if(stocks == null || stocks.isEmpty())
    		return false;

    	Node temp = stocks;

    	while(temp != null)
    	{
    		Stock stock = (Stock)temp.getValue();
    		if(stock.getName().equals(stockName))
    			return true;

    		temp = temp.getNext();
    	}

    	return false;
    }

    //------------------------------------------------------
	// removeStock(String stockName)
	//
	// PURPOSE:  remove particular stock from the Account object
	// PARAMETERS:
	//   String
	// Returns: Boolean
    public boolean removeStock(String stockName)
    {
    	if(stocks == null || stocks.isEmpty())
    		return false;

    	Node temp = stocks;

    	while(temp != null)
    	{
    		Stock stock = (Stock)temp.getValue();
    		if(stock.getName().equals(stockName))
    			break;

    		temp = temp.getNext();
    	}

    	if(temp == null)
    		return false;

    	stocks.remove((Stock)temp.getValue());

    	return true;
    }

	//------------------------------------------------------
	// addStock(Stock stock)
	//
	// PURPOSE:  add stock to Account Object
	// PARAMETERS:
	//   String
	// Returns: None
    public void addStock(Stock stock)
    {
        if(stocks == null)
        stocks = new Node(stock);
        else
        stocks.add(stock);
    }

	//------------------------------------------------------
	// getter methods
	//
	// PURPOSE:  get AccountName, balance, and stocks
	// PARAMETERS:
	//   None
	// Returns: string, double, Node
	public String getAccountNo() {
		return accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public Node getStocks() {
		return stocks;
	}


	//------------------------------------------------------
	// setter methods
	//
	// PURPOSE:  set AccountName, balance, and stocks
	// PARAMETERS:
	//   String, double, Node
	// Returns: None
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setStocks(Node stocks) {
		this.stocks = stocks;
	}


}
// CLASS: Transaction
//
// Author: Sijin Lee, 7822352
//
// REMARKS: Implement Transaction between the value of  bids and asks
//
//-----------------------------------------

public class Transaction
{
	//linkedlist of objects of Order class
    private static Node bids = new Node();
    private static Node asks = new Node();

    public static final double FEE = 9.99;
    private static String message;


	//------------------------------------------------------
	// stockStatus(String stockName)
	//
	// PURPOSE:  to print status of stock based on the value of parameter
	// PARAMETERS:
	//   String
	// Returns: message
    public static String stockStatus(String stockName)
    {
    	Stock stock = StockMarket.getStock(stockName);

    	if(stock == null)
    		return "NOT FOUND\n";

    	message = "Stock: "+stockName+" has current price of $"+stock.getPrice()+"\n";
    	message = message + "Here is the list of bids:\n";

    	Node temp = bids;

    	while(temp != null && !temp.isEmpty())
    	{
    		Order order = (Order)temp.getValue();

    		if(order.getStock().getName().equals(stockName))
    		{
    			message = message+"Account#"+order.getSource().getAccountNo()
    					+": " + order.getStock()+"\n";

    		}

    		temp = temp.getNext();
    	}

    	message = message + "Here is the list of asks:\n";
    	temp = asks;

    	while(temp != null && !temp.isEmpty())
    	{
    		Order order = (Order)temp.getValue();

    		if(order.getStock().getName().equals(stockName))
    		{
    			message = message+"Account#"+order.getSource().getAccountNo()
    					+": " + order.getStock()+"\n";
    		}

    		temp = temp.getNext();
    	}

    	return message;

    }


	//------------------------------------------------------
	// remove(Order order, boolean isBid)
	//
	// PURPOSE:  This action removes an ask order from the system,
	// PARAMETERS:
	//   Order, boolean
	// Returns: "ACCOUNT NOT FOUND", "STOCK NOT FOUND", "ORDER NOT FOUND", and "CONFIRMED"

    public static String remove(Order order,boolean isBid)
    {
    	Node source;

    	if(isBid)
    		source = bids;
    	else
    		source = asks;

    	if(source == null || source.isEmpty())
    	{
    		return "ORDER NOT FOUND\n";
    	}

    	if(!StockMarket.isPresent(order.getStock().getName()))
    	{
    		return "STOCK NOT FOUND\n";
    	}

    	if(!InvestmentAccounts.isPresent(order.getSource().getAccountNo()))
    	{
    		return "ACCOUNT NOT FOUND\n";
    	}

    	boolean isRemoved = source.remove(order);

    	if(!isRemoved)
    	return "ORDER NOT FOUND\n";

    	return "CONFIRMED";

    }

	//------------------------------------------------------
	// hasDuplicateOrder(Order order,boolean isBid)
	//
	// PURPOSE: to check for duplicate order
	// PARAMETERS:
	//   Order, boolean
	// Returns: Boolean

    private static boolean hasDuplicateOrder(Order order,boolean isBid)
    {

    	Node source;

    	if(isBid)
    		source = bids;
    	else
    		source = asks;

    	if(source == null || source.isEmpty())
    		return false;

    	Node temp = source;

    	while(temp != null)
    	{
    		Order value = (Order)temp.getValue();

    		if(value.equals(order)) //equals override of Order will take care of it
    			return true;

    		temp = temp.getNext();
    	}

    	return false;
    }

	//------------------------------------------------------
	// transact(Order bid,Order ask)
	//
	// PURPOSE: //change price of stock, update balance&stocks for both - bid&ask, and removing both orders from the system..
	//
	// PARAMETERS:
	//   Order, Order
	// Returns: None
	private static void transact(Order bid,Order ask)
    {


    	Stock stock = StockMarket.getStock(bid.getStock().getName()); //Very Imp
    	stock.setPrice(bid.getPrice());

    	Account bidder = InvestmentAccounts.getAccount(bid.getSource().getAccountNo());
    	Account asker = InvestmentAccounts.getAccount(ask.getSource().getAccountNo());

    	double bidBalance = bidder.getBalance();
    	bidBalance = bidBalance - (bid.getPrice()*bid.getQuant()) - FEE;
    	bidder.setBalance(bidBalance);

    	double askBalance = asker.getBalance();
    	askBalance = askBalance + (bid.getPrice()*bid.getQuant()) - FEE;
    	asker.setBalance(askBalance);

    	bidder.addStock(new Stock(stock.getName(),bid.getPrice(),bid.getQuant()));
    	asker.removeStock(stock.getName());

    	bids.remove(bid);
    	asks.remove(ask);

    }

	//------------------------------------------------------
	// validateBid(Order order,boolean isBid)
	//
	// PURPOSE: to check if the bid is valid
	//
	// PARAMETERS:
	//   Order, boolean
	// Returns: Boolean
    private static boolean validateBid(Order order,boolean isBid)
    {
        //ACCOUNT NOT FOUND, STOCK NOT FOUND, INVALID ORDER, DUPLICATE, CONFIRMED,
        //TRANSACTION COMPLETED

        Account acc = InvestmentAccounts.getAccount(order.getSource().getAccountNo());
        Stock stock = StockMarket.getStock(order.getStock().getName());

    	if(acc == null)
        {
    		message = "ACCOUNT NOT FOUND\n";
    		return false;
        }

        if(stock == null)
        {
        	message = "STOCK NOT FOUND\n";
        	return false;
        }

        if(isBid)
        {
        	double amount = order.getPrice()*order.getQuant();

        	if(amount + FEE > acc.getBalance())
        	{
        		message = "INVALID ORDER - Insufficient funds!\n";
        		return false;
        	}
        }
        else
        {
        	String stockName = stock.getName();
        	if(!acc.hasStock(stockName))
        	{
        		message = "INVALID ORDER - Stock is not withheld!\n";
        		return false;
        	}
        }

        if(hasDuplicateOrder(order,isBid))
        {
        	message = "DUPLICATE ORDER\n";
        	return false;
        }

        return true;
    }


	//------------------------------------------------------
	// addBid(Order bid)
	//
	// PURPOSE: to check if in asks, an order is there with val<=bid.
	//			if not, add this node to bids
	// PARAMETERS:
	//   Order
	// Returns: None
    public static String addBid(Order bid)
    {
    	message = "";

    	if(!validateBid(bid,true))
    	{
    		return message;
    	}

    	if(asks == null || asks.isEmpty())
        {
            bids.add(bid);
            return "CONFIRMED";
        }

        Node temp = asks;

        while(temp != null)
        {
            Order order = (Order)(temp.getValue());
            if(order.getStock().getName().equals(bid.getStock().getName()) &&
            			order.getPrice() <= bid.getPrice())
            break;

            temp = temp.getNext();
        }

        if(temp == null)
        {
        	bids.add(bid);
            return "CONFIRMED";
        }

        transact(bid,(Order)temp.getValue());

        return "TRANSACTION COMPLETED";

    }

	//------------------------------------------------------
	// addAsk(Order ask)
	//
	// PURPOSE: to check if in bids, an order is there with bid>=ask.
	//
	// PARAMETERS:
	//   Order
	// Returns: "", "CONFIRMED", and  "TRANSACTION COMPLETED"
    public static String addAsk(Order ask)
    {
    	message = "";

    	if(!validateBid(ask,false))
    	{
    		return message;
    	}

    	if(bids == null || bids.isEmpty())
        {
            asks.add(ask);
            return "CONFIRMED";
        }

        Node temp = bids;

        Order highestBid = null;

        while(temp != null)
        {
            Order order = (Order)(temp.getValue());
            if(order.getStock().getName().equals(ask.getStock().getName()) &&
            		order.getPrice() >= ask.getPrice())
            {
            	if(highestBid == null || order.getPrice() > highestBid.getPrice())
            		highestBid = order;
            }

            temp = temp.getNext();
        }

        if(highestBid == null)
        {
        	asks.add(ask);
            return "CONFIRMED";
        }

        transact(highestBid,ask);

        return "TRANSACTION COMPLETED";

    }

}
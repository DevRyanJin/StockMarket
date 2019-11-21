// CLASS: InvestmentAccounts
//
// Author: Sijin Lee, 7822352
//
// REMARKS: Send asks (orders to sell) for a particular stock
//
//-----------------------------------------

public class InvestmentAccounts
{
	private static Node root; //Linked list of Accounts..


    //------------------------------------------------------
    // add(Account acc)
    //
    // PURPOSE:  to add a Account to system and check if it is already in system or not
    // PARAMETERS:
    //   Stock
    // Returns: "CONFIRMED" or "DUPLICATE" or "STOCK NOT FOUND"
    public static String add(Account acc)
    {
    	if(root == null || root.isEmpty())
        {
        	root = new Node(acc);
        	root.add(acc);
        	return "CONFIRMED\n";
        }
        else if(isPresent(acc.getAccountNo()))
        {
        	return "DUPLICATE\n";
        }
        else if(!allStocksPresent(acc.getStocks()))
        {
        	return "STOCK NOT FOUND\n";
        }
        else
        {
        	root.add(acc);
        	return "CONFIRMED\n";
        }
    }

    //------------------------------------------------------
    // allStocksPresent(Node stocks)
    //
    // PURPOSE:  helper method to check the stock is already in list
    // PARAMETERS:
    //   Node
    // Returns: Boolean

    public static boolean allStocksPresent(Node stocks)
    {
    	if(stocks == null || stocks.isEmpty())
    		return true;

    	while(stocks != null)
    	{
    		Stock stock = (Stock)stocks.getValue();
    		if(!StockMarket.isPresent(stock.getName()))
    			return false;

    		stocks = stocks.getNext();
    	}

    	return true;
    }

    //------------------------------------------------------
    //isPresent(String accNo)
    //
    // PURPOSE:  helper method to check the name of the accountNumber is already in list
    // PARAMETERS:
    //   String
    // Returns: Boolean
    public static boolean isPresent(String accNo)
    {
        if(root == null || root.isEmpty())
        return false;

        Node temp = root;

        while(temp != null)
        {
            Account acc = (Account)temp.getValue();
            if(acc.getAccountNo().equals(accNo))
            return true;

            temp = temp.getNext();
        }

        return false;
    }

    //------------------------------------------------------
    // getAccount(String accNo)
    //
    // PURPOSE:  find the given accountNumber. If so, return corresponding Account for that accountNumber
    // PARAMETERS:
    //   String
    // Returns: Account
    public static Account getAccount(String accNo)
    {
    	if(root == null || root.isEmpty())
        return null;

        Node temp = root;

        while(temp != null)
        {
            Account acc = (Account)temp.getValue();
            if(acc.getAccountNo().equals(accNo))
            return acc;

            temp = temp.getNext();
        }

        return null;
    }

    //------------------------------------------------------
    // balance(String accNo)
    //
    // PURPOSE:  find the given accountNumber and prints the status of an investing account
    //            current balance and which stocks and what price is in it
    //
    // PARAMETERS:
    //   String
    // Returns: String
    public static String balance(String accNo)
    {
    	Account acc = getAccount(accNo);

        if(acc == null)
        return "NOT FOUND\n";

        String message = "Account #"+accNo+"\n";
        message = message + "Balance: " + acc.getBalance() + "\n";
        message = message + "Following stocks :\n";

        Node stocks = acc.getStocks();

        while(stocks != null)
        {
        	Stock stock = (Stock)stocks.getValue();
        	message = message + stock + "\n";
        	stocks = stocks.getNext();
        }

        return message;
    }

}

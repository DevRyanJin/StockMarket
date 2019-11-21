// CLASS: StockMarket
//
// Author: Sijin Lee, 7822352
//
// REMARKS: implement actual stock market system
//
//-----------------------------------------


public class StockMarket
{
	private static Node root; //reference of linked list holding objects of Stocks


    //------------------------------------------------------
    // String(Stock stock)
    //
    // PURPOSE:  to add a stock to system and check if it is already in system or not
    // PARAMETERS:
    //   Stock
    // Returns: "CONFIRMED" or "DUPLICATE"
    public static String add(Stock stock)
    {
        if(root == null || root.isEmpty())
        {
        	root = new Node(stock);
        	root.add(stock);
        	return "CONFIRMED";
        }
        else if(isPresent(stock.getName()))
        {
        	return "DUPLICATE";
        }
        else
        {
        	root.add(stock);
        	return "CONFIRMED";
        }
    }

    //------------------------------------------------------
    // isPresent(String name)
    //
    // PURPOSE:  helper method to check the name of stock is already in list
    // PARAMETERS:
    //   String
    // Returns: Boolean

    public static boolean isPresent(String name)
    {
        if(root == null || root.isEmpty())
        return false;

        Node temp = root;

        while(temp != null)
        {
            Stock stock = (Stock)temp.getValue();

            if(stock.getName().equals(name))
            return true;

            temp = temp.getNext();
        }

        return false;
    }

    //------------------------------------------------------
    // getStock(String stockName)
    //
    // PURPOSE:  to get corresponding name of stock
    // PARAMETERS:
    //   String
    // Returns: Stock
    public static Stock getStock(String stockName)
    {
    	if(root == null || root.isEmpty())
        return null;

        Node temp = root;

        while(temp != null)
        {
            Stock stock = (Stock)temp.getValue();

            if(stock.getName().equals(stockName))
            return stock;

            temp = temp.getNext();
        }

        return null;
    }
}
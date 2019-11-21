//-----------------------------------------
// NAME		: Sijin Lee
// STUDENT NUMBER	: 7822352
// COURSE		: COMP 2150
// INSTRUCTOR	: Heather Matheson
// ASSIGNMENT	: assignment 1
// QUESTION	: question 1
//
// REMARKS: Implement Stock market using students's own data structure(linked list)
//
//
//-----------------------------------------

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		final String FILENAME = args[0];

		BufferedReader br = null;
		FileReader fr = null;

		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);

		String line;

		while((line = br.readLine()) != null)
	    {
	        if(line.startsWith("#"))
	        {
	            System.out.println(line);

	        }
	        else if(line.startsWith("STOCK"))
	        {
	            int index = line.indexOf(" ");
	            String name = line.substring(index+1);

	            Stock stock = new Stock(name,0,-1);

	            String message = StockMarket.add(stock);

	            System.out.println(message);
	        }
	        else if(line.startsWith("NEW"))
	        {
	            int index = line.indexOf(" ");
	            line = line.substring(index+1);

	            index = line.indexOf(" ");
	            String accNo = line.substring(0,index);

	            line = line.substring(index+1);
	            index = line.indexOf(" ");

	            String balance;
	            String stockString = null;

	            if(index == -1)
	            balance = line;
	            else
	            {
	                balance = line.substring(0,index);
	                stockString = line.substring(index+1);
	            }

	            Account acc = new Account(accNo,Double.parseDouble(balance));

	            while(stockString != null)
	            {
	                int index1 = stockString.indexOf(" ");
	                String singleStock;

	                if(index1 == -1)
	                {
	                    singleStock = stockString;
	                    stockString = null;
	                }
	                else
	                {
	                    singleStock = stockString.substring(0,index1);
	                    stockString = stockString.substring(index1+1);
	                }

	                index1 = singleStock.indexOf("-");
	                String stockName = singleStock.substring(0,index1);
	                singleStock = singleStock.substring(index1+1);
	                index1 = singleStock.indexOf("-");
	                int quant = Integer.parseInt(singleStock.substring(0,index1));
	                double price = Double.parseDouble(singleStock.substring(index1+1));

	                Stock stock = new Stock(stockName,price,quant);

	                acc.addStock(stock);
	            }

	            String message = InvestmentAccounts.add(acc);
	            System.out.println(message);
	        }
	        else if(line.startsWith("ADD"))
	        {
	            boolean isBid;
	            Order order;

	            String[] tokens = line.split(" ");

	            if(tokens[1].equals("ASK"))
	            isBid = false;
	            else
	            isBid = true;

	            String accNo = tokens[2];
	            String stockName = tokens[3];
	            int quant = Integer.parseInt(tokens[4]);
	            double price = Double.parseDouble(tokens[5]);

	            Account acc = new Account(accNo,-1);
	            Stock stock = new Stock(stockName,price,quant);

	            order = new Order(stock,price,acc,quant);
	            String message;

	            if(isBid)
	            {
	                message = Transaction.addBid(order);
	            }
	            else
	            {
	                message = Transaction.addAsk(order);
	            }

	            System.out.println(message);
	        }
	        else if(line.startsWith("REMOVE"))
	        {
	            String[] tokens = line.split(" ");
	            boolean isBid;

	            if(tokens[1].equals("ASK"))
	            isBid = false;
	            else
	            isBid = true;

	            String accNo = tokens[2];
	            String stockName = tokens[3];

	            Account acc = new Account(accNo,-1);
	            Stock stock = new Stock(stockName,0,-1);

	            Order order = new Order(stock,0,acc,-1);

	            String message = Transaction.remove(order,isBid);

	            System.out.println(message);
	        }
	        else if(line.startsWith("BALANCE"))
	        {
	            int index = line.indexOf(" ");
	            String accNo = line.substring(index+1);

	            String message = InvestmentAccounts.balance(accNo);

	            System.out.println(message);
	        }
	        else if(line.startsWith("STATUS"))
	        {
	            int index = line.indexOf(" ");
	            String name = line.substring(index+1);

	            String message = Transaction.stockStatus(name);

	            System.out.println(message);
	        }
	        else if(line.startsWith("QUIT"))
	        {
	            String message = "DONE";

	            System.out.println(message);

	            break;
	        }
	        else
	        {
	            String message = "Invalid Input";

	            System.out.println(message);
	        }
	    }

	}
}

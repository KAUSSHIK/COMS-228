package edu.iastate.cs228.hw1;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author <<Kausshik Manojkumar>>
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		//TODO: Write your code here.
		for(int i = 0; i < tOld.getLength(); i++) {
			for(int j = 0; j < tOld.getWidth(); j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
			}
		}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		//Profit comes from Casual users only!!!
		int profit = 0;
		for(int i = 0; i < town.getLength(); i++) {
			for(int j = 0; j < town.getWidth(); j++) {
				if((town.grid[i][j]).who().equals(State.CASUAL)) {
					profit++;
				}
			}
		}
		return profit;
	}
	//used for decimal formatting in the end while displaying final result
	private static final DecimalFormat dfZero = new DecimalFormat("0.00");
	
	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		//TODO: Write your code here.
		Scanner scnr = new Scanner(System.in);
		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed : ");
		int choice = scnr.nextInt();
		int length = 0;
		int width = 0;
		int seed = 0;
		Town town = null;
		if(choice == 1) {
			//using temporary scanners to simplify work
			Scanner tempScnr = new Scanner(System.in);
			System.out.println("Enter file path: ");
			String filePath = tempScnr.nextLine();
			try {
				town = new Town(filePath);
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found, enter again: ");
				tempScnr.close();
			}
		}
		else if(choice == 2) {
			Scanner tempScnr = new Scanner(System.in);
			System.out.println("Enter number of Rows, Coloumns and the Seed Integer separated by a space: ");
			length = tempScnr.nextInt();
            width = tempScnr.nextInt();
            seed = tempScnr.nextInt();
            town = new Town(length, width);
            town.randomInit(seed);
            tempScnr.close();
		}
		
		int profit = 0;
		for(int i = 1; i <= 12; i++) {
			//PRINT GRID
			System.out.print(town.toString());
            System.out.print("\n");
            //PRINT PROFITS
            int printProfit = getProfit(town);
            profit += printProfit;
            System.out.println("Profit: " + printProfit);
            town =  updatePlain(town);
            if(i != 12)
            	System.out.println("After iteration: " + i);
		}
		int potentialProfit = town.getLength() * town.getWidth();
		double percentProfit = (profit*100.0)/(12*potentialProfit);
		//formatting the output
		System.out.println(dfZero.format(percentProfit)+ "%");
	}
}

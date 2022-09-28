package edu.iastate.cs228.hw1;


/**
 * 
 * @author <<Kausshik Manojkumar>>
 *	Also provide appropriate comments for this class
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	//Update Neighbor given state and the array census, reduces redundancy
	private void updateNeigh(State x, int nCensus[]) {
		if(x.equals(State.RESELLER)) {
			nCensus[RESELLER]++;
		}
		else if(x.equals(State.EMPTY)) {
			nCensus[EMPTY]++;
		}
		else if(x.equals(State.CASUAL)) {
			nCensus[CASUAL]++;
		}
		else if(x.equals(State.OUTAGE)) {
			nCensus[OUTAGE]++;
		}
		else if(x.equals(State.STREAMER)) {
			nCensus[STREAMER]++;
		}
	}
	
	/**
	 * Checks all neighborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 
		boolean upPointer = false;
		boolean downPointer = false;
		boolean leftPointer = false;
		boolean rightPointer = false;
		//This method is the heart of the code, it tells us the neighborhood of a cell;
		//A cell can at most have 8 neighbors at various positions
		//They are: (r-1,c-1) , (r-1,c) , (r-1,c+1) ; (r,c-1), (r,c+1) ; (r+1,c-1) , (r+1,c) , (r+1,c+1)
		//Think of it as a GPS, if you want to go NORTH EAST you need to be able to go NORTH and EAST both from a given place
		if(row-1 != -1)
			upPointer = true;
		if(col-1 != -1)
			leftPointer = true;
		if(row+1 != plain.getLength())
			downPointer = true;
		if(col+1 != plain.getWidth())
			rightPointer = true;
		
		if(upPointer) {
			State x = plain.grid[row - 1][col].who();
			updateNeigh(x,nCensus);
		}
		if(leftPointer) {
			State x = plain.grid[row][col - 1].who();
			updateNeigh(x,nCensus);
		}
		if(downPointer) {
			State x = plain.grid[row + 1][col].who();
			updateNeigh(x,nCensus);
		}
		if(rightPointer) {
			State x = plain.grid[row][col + 1].who();
			updateNeigh(x,nCensus);
		}
		if(upPointer && rightPointer) {
			State x = plain.grid[row - 1][col + 1].who();
			updateNeigh(x,nCensus);
		}
		if(upPointer && leftPointer) {
			State x = plain.grid[row - 1][col - 1].who();
			updateNeigh(x,nCensus);
		}
		if(downPointer&rightPointer) {
			State x = plain.grid[row + 1][col + 1].who();
			updateNeigh(x,nCensus);
		}
		if(downPointer&leftPointer) {
			State x = plain.grid[row + 1][col - 1].who();
			updateNeigh(x,nCensus);
		}
			

	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}

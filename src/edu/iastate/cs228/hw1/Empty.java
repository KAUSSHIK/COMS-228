package edu.iastate.cs228.hw1;
/**
 * Empty class implementation
 * @author kausshik
 *
 */
public class Empty extends TownCell {

	public Empty(Town p, int r, int c) {
		super(p, r, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public State who() {
		return State.EMPTY;
	}

	@Override
	public TownCell next(Town tNew) {
		this.census(nCensus);
		if(nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
			return new Reseller(tNew, row, col);
		}
		return new Casual(tNew, row, col);
	}

}

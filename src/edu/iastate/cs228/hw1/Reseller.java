package edu.iastate.cs228.hw1;
/**
 * Reseller class implementation
 * @author kausshik
 *
 */
public class Reseller extends TownCell {

	public Reseller(Town p, int r, int c) {
		super(p, r, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public State who() {
		return State.RESELLER;
	}

	@Override
	public TownCell next(Town tNew) {
		this.census(nCensus);
		if(nCensus[CASUAL] <= 3 || nCensus[EMPTY] >= 3) {
			return new Empty(tNew, row, col);
		}
		else if(nCensus[CASUAL] >= 5) {
			return new Streamer(tNew, row, col);
		}
		return new Reseller(tNew, row, col);
	}

}

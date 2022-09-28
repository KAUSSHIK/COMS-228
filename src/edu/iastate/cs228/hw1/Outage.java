package edu.iastate.cs228.hw1;
/**
 * Outage class implementation
 * @author kausshik
 *
 */
public class Outage extends TownCell {

	public Outage(Town p, int r, int c) {
		super(p, r, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public State who() {
		return State.OUTAGE;
	}

	@Override
	public TownCell next(Town tNew) {
		this.census(nCensus);
		if(nCensus[CASUAL] >= 5) {
			return new Streamer(tNew, row, col);
		}
		return new Empty(tNew, row, col);
	}

}

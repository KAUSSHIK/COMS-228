package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TownCellTest {

	@Test
	void test() {
        Town t = new Town(2,2);
        t.grid[0][0] = new Empty(t,0,0);
        t.grid[0][1] = new Reseller(t,0,1);
        t.grid[1][0] = new Reseller(t,1,0);
        t.grid[1][1] = new Outage(t,1,1);
        t.grid[0][0].census(t.grid[0][0].nCensus);
        assertEquals(t.grid[0][0].nCensus[0], 2);
        assertEquals(t.grid[0][0].nCensus[3], 1);
    }

}

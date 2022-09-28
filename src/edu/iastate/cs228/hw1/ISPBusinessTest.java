package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ISPBusinessTest {

	@Test
	void testGetProfit() {
		Town t = new Town(2,2);
        t.grid[0][0] = new Empty(t,0,0);
        t.grid[0][1] = new Empty(t,0,1);
        t.grid[1][0] = new Casual(t,1,0);
        t.grid[1][1] = new Casual(t,1,1);
		assertEquals(2, ISPBusiness.getProfit(t));
	}
	
	@Test
	void testUpdatePlain() {
		Town t = new Town(2,2);
        t.grid[0][0] = new Outage(t,0,0);
        t.grid[0][1] = new Casual(t,0,1);
        t.grid[1][0] = new Reseller(t,1,0);
        t.grid[1][1] = new Casual(t,1,1);
        
		Town t1 = new Town(2,2);
        t1.grid[0][0] = new Empty(t1,0,0);
        t1.grid[0][1] = new Reseller(t1,0,1);
        t1.grid[1][0] = new Empty(t1,1,0);
        t1.grid[1][1] = new Reseller(t1,1,1);
       
        assertEquals(ISPBusiness.updatePlain(t).toString(), t1.toString());
	}
	

}

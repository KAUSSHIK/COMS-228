package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class TownTest {

    @Test
    void testLength()
    {
    	Town t;
    	t = new Town(3, 345);
		t.randomInit(3);
    	assertEquals(3, t.getLength());
    }
    
    @Test
    void testWidth() {
    	Town t;
    	t = new Town(5, 4);
		t.randomInit(3);
    	assertEquals(4, t.getWidth());
    }
    

}

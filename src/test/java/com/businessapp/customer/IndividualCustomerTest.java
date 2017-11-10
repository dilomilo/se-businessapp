/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.businessapp.customer;

import com.businessapp.model.IndividualCustomer;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import java.util.Date;

/**
 *
 * @author dil3k
 */
public class IndividualCustomerTest {
    
    
    IndividualCustomer a= new IndividualCustomer();
    

    /**
     * Test of getFirstName method, of class IndividualCustomer.
     */
    @Test
    public void testGetFirstName() {
    
        a.setFirstName("Meyer");
        //final String testStr="Meyer";
        assertEquals("Meyer",a.getFirstName());
        
    }

    /**
     * Test of setFirstName method, of class IndividualCustomer.
     */
    @Test
    public void testSetFirstName() {
        
    }
    
    public void testLeererString(){
        
        a.setFirstName("");
        assertTrue(a.getFirstName().isEmpty());
    }
    
    @Test
    public void testNull(){
    
    a.setFirstName(null);
    assertTrue(a.getFirstName()==null);
    }
    
    @Test
    public void testId() {
        
        a.setId("1234");
	assertEquals("1234",a.getId());
	}


    public void testCreated() {
        
        
        
        
        
            
             a.setCreated(new Date(1989,8,21));
             assertEquals(new Date(1989,8,21),a.getCreated());
	}

	
}


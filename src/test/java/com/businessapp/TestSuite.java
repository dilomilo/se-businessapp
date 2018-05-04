package com.businessapp;
import com.businessapp.customer.IndividualCustomerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
   AppTest.class,
   IndividualCustomerTest.class
})

public class TestSuite {   
}  
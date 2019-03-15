package org.loiz.demo;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoizAutreBootSpringApplicationTests {

	@Test 
	@DisplayName("*****************************************************")
	public void testDummy1()
	{
		System.out.println("premier test.....");
		boolean bol = true; 
		org.junit.Assert.assertTrue( bol==true );		
	}

}

package com.example.RewardsCalculation;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.rewardsDAO.RewardsRepositoryImp;
import com.example.rewardsService.Rewards;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class RewardsCalculationApplicationTests {

	@Autowired
	RewardsRepositoryImp rewardsRepositoryImp;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	
	  @Sql({ "schema.sql", "data.sql" })
	  @Test
	  public void getAllTransactions() 
	  {
	    assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/transactions", Rewards.class).getPrice().size()==10);
	         
	  }
	
	@Test
	public void rewardsCalculation() {
		  List<Integer> list = null;
		  list.add(120);
		  Mockito.when(rewardsRepositoryImp.calculateRewardPoints(list)).thenReturn(list);
	      Integer testName = list.get(1);
	      Assert.assertEquals("90", testName);
	}

}

package com.casestudy.stockmarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.casestudy.stockmarket.model.StockMarket;
import com.casestudy.stockmarket.repository.Stock_Repository;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureMockMvc
public class StockmarketRepositoryTest {
	@Autowired
	private Stock_Repository userRepository;
	
	private StockMarket user = new StockMarket();// real object
	
	@BeforeEach
	public void init()
	{
		user.setCompanyCode(205);
		user.setCompanysName("GTS");
		user.setCompanysCEO("abc");
		user.setCompanyTurnover(101876666667777.09);
		user.setCompanyWebsite("www.abc");
		user.setStockExchange("BSE");
	
		
	}
	
	@Test
	public void saveUserSuccess() throws Exception
	{
		StockMarket u1 = null;
		userRepository.save(user);
		
		u1= userRepository.findById(user.getCompanyCode()).get();
		
		assertEquals(user.getCompanysName() , u1.getCompanysName() );
		
	}
	
	@Test
	public void saveUserFailure() throws Exception
	{
		StockMarket u1 = null;
		
		if(userRepository.findAll().toString().isEmpty())
		{
			userRepository.save(user);
			u1= userRepository.findById(user.getCompanyCode()).get();
		}
		
		assertNull(u1);
		
	}

}









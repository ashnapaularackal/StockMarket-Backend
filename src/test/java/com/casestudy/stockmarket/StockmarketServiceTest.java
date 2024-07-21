package com.casestudy.stockmarket;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.casestudy.stockmarket.model.StockMarket;
import com.casestudy.stockmarket.repository.Stock_Repository;
import com.casestudy.stockmarket.service.StockServiceImpl;
import com.casestudy.stockmarket.service.Stock_Service;

import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
public class StockmarketServiceTest {

	@Mock
	private Stock_Repository userRepo;
	
	@InjectMocks
	private Stock_Service userService=new StockServiceImpl();
	
	@Autowired
	private MockMvc mockMvc;
	

	@BeforeEach
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	
		mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
	}
	

 
	private List<StockMarket> userList = new ArrayList<>();
	@Test
	public void getAllCompanySuccess() throws Exception
	{
		StockMarket user = new StockMarket();

		user.setCompanyCode(206);
		user.setCompanysName("GTS");
		user.setCompanysCEO("abc");
		user.setCompanyTurnover(101876666667777.0);
		user.setCompanyWebsite("www.abc");
		user.setStockExchange("BSE");
		user.setStockprice(8776655);
		user.setTime(LocalDateTime.now());
		 
		userList.add(user);
		when(userRepo.findAll()).thenReturn(userList);
		
		List<StockMarket> uList = userService.getAllCompanyDetails();
		assertEquals(userList, uList);
	
	}
	
	@Test
	public void getAllCompanyFailure() throws Exception
	{
		
		
		when(userRepo.findAll()).thenReturn(null);
		
		List<StockMarket> uList = userService.getAllCompanyDetails();
		assertNull(uList);
	
	}
	
	@Test
	public void addCompanySuccess() throws Exception
	{
		StockMarket user = new StockMarket();

		user.setCompanyCode(206);
		user.setCompanysName("GTS");
		user.setCompanysCEO("abc");
		user.setCompanyTurnover(101876666667777.09);
		user.setCompanyWebsite("www.abc");
		user.setStockExchange("BSE");
		user.setStockprice(8776655.9);
		user.setTime(LocalDateTime.now());
		
		userList.add(user);
		when(userRepo.saveAndFlush(any())).thenReturn(user);
		System.out.println(user);
		
		StockMarket u1 = userService.addCompany(user);
		
		System.out.println(u1);
		assertEquals(user, u1);
	
	}
	@Test
	public void addCompanyFailure() throws Exception
	{
		StockMarket user = new StockMarket();

		user.setCompanyCode(208);
		user.setCompanysName("GTS");
		user.setCompanysCEO("abc");
		user.setCompanyTurnover(101876666667777.09);
		user.setCompanyWebsite("www.abc");
		user.setStockExchange("BSE");
	
		 userList.add(user);
		 
		
		when(userRepo.saveAndFlush(any())).thenReturn(null);
		
		StockMarket u1 = userService.addCompany(user);
		
		
		assertNull(u1);
	
	}

}











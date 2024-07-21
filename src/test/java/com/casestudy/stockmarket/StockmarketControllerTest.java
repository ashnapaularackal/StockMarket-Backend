package com.casestudy.stockmarket;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.casestudy.stockmarket.controller.StockMarketController;
import com.casestudy.stockmarket.model.StockMarket;
import com.casestudy.stockmarket.service.Stock_Service;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class StockmarketControllerTest {

	@Mock
	private Stock_Service userService;
	
	@InjectMocks
	private StockMarketController userController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
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
	
		
		userList.add(user);
		when(userService.getAllCompanyDetails()).thenReturn(userList);
		
		
		assertEquals(1,userList.size());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/market/company/getall").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());
	
	}
	@Test
	public void getAllCompanyFailure() throws Exception
	{
		userList.clear();
		
		when(userService.getAllCompanyDetails()).thenReturn(userList);
		assertEquals(0, userList.size());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/market/company/getall").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void addCompanySuccess() throws Exception
	{
		StockMarket user = new StockMarket();

		user.setCompanyCode(207);
		user.setCompanysName("GTS");
		user.setCompanysCEO("abc");
		user.setCompanyTurnover(101876666667777.0);
		user.setCompanyWebsite("www.abc");
		user.setStockExchange("BSE");
	
		
		userList.add(user);
		when(userService.addCompany(any())).thenReturn(user);
		
			assertEquals(1, userList.size());
			
			
			mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/market/company/register").contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(user)))
			.andExpect(MockMvcResultMatchers.status().isCreated());		
			
	
	}
	@Test
	public void addCompanyFailure() throws Exception
	{
		StockMarket user = new StockMarket();

		
		user.setCompanyCode(208);
		user.setCompanysName("GTS");
		user.setCompanysCEO("abc");
		user.setCompanyTurnover(101876666667777.0);
		user.setCompanyWebsite("www.abc");
		user.setStockExchange("BSE");
	
		
		  
		 userList.add(user);
		 
		
		when(userService.addCompany(any())).thenReturn(null);
		
		StockMarket u1 = userService.addCompany(user);
		
		
		assertNull(u1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/market/company/register").contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(user)))
		.andExpect(MockMvcResultMatchers.status().isConflict());	
	
	}


}


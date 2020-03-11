package com.assignment.FlagPick.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.FlagPick.FlagPickApplication;
import com.assignment.FlagPick.model.Continent;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=FlagPickApplication.class)
public class FlagServiceTest {
	
	@Autowired
	FlagService flagService;
	
	@Test
	public void testGetAllContinents()
	{
		List<Continent> allContinents = flagService.getAllContinents();
		Assert.assertNotNull(allContinents);
		Assert.assertTrue(allContinents.size() > 0);
	}
	
	@Test
	public void testSearchContinent()
	{
		String searchString = "eur";
		List<Continent> srchContinents = flagService.searchContinentList(searchString);
		Assert.assertNotNull(srchContinents);
		Assert.assertTrue(srchContinents.size() == 1);
	}
	
	@Test
	public void testSearchContinents()
	{
		String searchString = "a";
		List<Continent> srchContinents = flagService.searchContinentList(searchString);
		Assert.assertNotNull(srchContinents);
		Assert.assertTrue(srchContinents.size() == 4);
	}

}

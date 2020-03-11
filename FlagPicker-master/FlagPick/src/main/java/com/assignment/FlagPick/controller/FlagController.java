package com.assignment.FlagPick.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.FlagPick.model.Continent;
import com.assignment.FlagPick.model.CountryFlag;
import com.assignment.FlagPick.service.FlagService;

@Controller
public class FlagController {
	
	private static Logger logger = Logger.getLogger(FlagController.class);
	
	@Autowired
	private FlagService flagService;
	
	@GetMapping(path="/")
	public String index()
	{
		logger.debug("Begin Controller index()");
		return "index";
	}
	
	@GetMapping(path="/continents")
	@ResponseBody
	public List<Continent> getAllContinents(ModelMap model)
	{
		logger.debug("Begin Controller getAllContinents()");
		List<Continent> allContinents = flagService.getAllContinents();
		model.put("continents", allContinents);
		logger.debug("End Controller getAllContinents()");
		return allContinents;
	}
	
	@GetMapping(path="/continents/{search}")
	@ResponseBody
	public List<Continent> searchContinents(@PathVariable String search)
	{
		logger.debug("Begin Controller searchContinents() for Search String : " + search);
		return flagService.searchContinentList(search);
	}
	
	@GetMapping(path="/continent/{continentName}/countries")
	@ResponseBody
	public List<CountryFlag> getCountriesForContinent(@PathVariable String continentName)
	{
		logger.debug("Begin Controller getCountriesForContinent() for Continent : " + continentName);
		return flagService.getCountriesForContinent(continentName);
	}
	
}

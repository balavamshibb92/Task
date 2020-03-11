package com.assignment.FlagPick.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.assignment.FlagPick.model.Continent;
import com.assignment.FlagPick.model.CountryFlag;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class FlagService {
	
	private static Logger logger = Logger.getLogger(FlagService.class);
	
	private static List<Continent> list = new ArrayList<>();
	
	public List<Continent> getAllContinents()
	{
		logger.debug("Begin Service getContinentList()");
		try
		{
			if ( list == null || list.isEmpty() )
			{
			    list = readJSONFile();
			}
		
			logger.debug("Continents : " + list);
		}
		catch(Exception ex)
		{
			logger.debug("Exception :: ", ex);
		}
		logger.debug("End Service getContinentList()");
		
		return list;
	}
	
	private List<Continent> readJSONFile()
	{
		List<Continent> continentList = new ArrayList<>();
		try {
	        Resource resource = new ClassPathResource("continents.json");
	        InputStream resourceInputStream = resource.getInputStream();
	        if (resourceInputStream == null) 
	        {
	            logger.debug("JSON File could not be loaded.");
	        }
	        else
	        {
	        	logger.debug("JSON File is read successfully");
	        	//create ObjectMapper instance
	     		ObjectMapper objectMapper = new ObjectMapper();
	     		try {
	     	        TypeFactory typeFactory = objectMapper.getTypeFactory();
	     	        CollectionType collectionType = typeFactory.constructCollectionType(
	     	                                            List.class, Continent.class);
	     	        continentList = objectMapper.readValue(resourceInputStream, collectionType);
	     	        logger.debug("Converted JSON to Object\n"+continentList);
	     	        
	     	    } catch (IOException exception) {
	     	        logger.debug("Exception while Parsing JSON", exception);
	     	    }
	     		
	        }
	      
	    } catch (Exception e) {
	        logger.debug("JSON File could not be loaded!");
	    }
		
		return continentList;
	}

	public List<Continent> searchContinentList(String searchString)
	{
		logger.debug("Begin Service searchContinentList() for Search String : " + searchString);
		
		List<Continent> filteredList = new ArrayList<>();
		try {
				if ( list == null || list.isEmpty() )
				{
					list = getAllContinents();
				}
	
				filteredList = 
						list.stream()
						   .filter(continent -> continent.getContinent().toLowerCase().contains(searchString.toLowerCase()))
						   .collect(Collectors.toList());
				
				logger.debug("Filtered List : " + filteredList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		logger.debug("End Service searchContinentList() for Search String : " + searchString);
		return filteredList;
	}
	
	public List<CountryFlag> getCountriesForContinent(String continentName)
	{
		logger.debug("Begin Service getCountriesForContinent() for Continent : " + continentName);
		List<CountryFlag> countriesList = new ArrayList<>();;
		try {
			if ( list == null || list.isEmpty() )
			{
				list = getAllContinents();
			}
			for ( Continent continent : list )
			{
				if ( continent.getContinent().equalsIgnoreCase(continentName))
				{
					countriesList = continent.getCountries();
					break;
				}
			}
				
			logger.debug("Countries List for Continent : " + continentName + " is " + countriesList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("End Service getCountriesForContinent() for Continent : " + continentName);
		return countriesList;
	}

}

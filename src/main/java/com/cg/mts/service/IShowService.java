package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.mts.entities.Movie;
import com.cg.mts.entities.Show;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.exception.ShowNotPresentException;

public interface IShowService {
	
	//Add show 
	Show addShow(Show show);
	
	//Update show
	Show updateShow(Show show) throws ShowNotFoundException;
	
	//Remove show
	Show removeShow(int showid) throws ShowNotFoundException;
	
	//View a show according to the show id
	Show viewShow(int showid) throws ShowNotFoundException;
	
	//View all the shows
	List<Show> viewAllShows() throws ShowNotPresentException;
	
	//View all the shows having a particular theatre id
	List<Show> viewShowList(int theatreid)throws ShowNotFoundException;
	
	//View all the shows on a particular date
	List<Show> viewShowList(LocalDate date)throws ShowNotFoundException;
	
	//View the movie details for a show
	Movie viewMovieDetails(int showId) throws ShowNotFoundException;
	

}

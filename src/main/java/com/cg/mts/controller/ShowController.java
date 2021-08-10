package com.cg.mts.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.dto.ShowDTO;
import com.cg.mts.entities.Movie;
import com.cg.mts.entities.Show;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.exception.ShowNotPresentException;
import com.cg.mts.service.IShowService;
import com.cg.mts.utils.DTOEntityConverter;
import com.cg.mts.utils.EntityDTOConverter;

@RestController
public class ShowController {

	@Autowired
	IShowService showService;
	
	@Autowired
	DTOEntityConverter dtoEntityConverter;
	
	@Autowired
	EntityDTOConverter entityDtoConverter;
	
	// Add show
	// Post Mapping since a show is to be added
	@PostMapping("/shows")
	public ResponseEntity<ShowDTO> addShow(@Valid @RequestBody ShowDTO showDto){
		Show newShow = dtoEntityConverter.convertToShowEntity(showDto);
		Show savedShow = showService.addShow(newShow);
		ShowDTO show = entityDtoConverter.convertToShowDTO(savedShow);
		return new ResponseEntity<>(show,HttpStatus.OK);
	}

	// Update show
	// Put Mapping since the entire show data is to be updated
	@PutMapping("/shows")
	public ResponseEntity<ShowDTO> updateShow(@RequestBody ShowDTO showDto) throws ShowNotFoundException{
		Show newShow = dtoEntityConverter.convertToShowEntity(showDto);
		Show savedShow = showService.updateShow(newShow);
		ShowDTO show = entityDtoConverter.convertToShowDTO(savedShow);
		return new ResponseEntity<>(show,HttpStatus.OK);
	}

	// Remove show
	// Delete Mapping since a screen is to be deleted
	@DeleteMapping("shows/{id}")
	public ResponseEntity<Show> removeShow(@PathVariable("id") int showId) throws ShowNotFoundException{
		return new ResponseEntity<>(showService.removeShow(showId),HttpStatus.OK);
	}

	// View a show according to the show id
	// Get Mapping since a show is to be returned
	@GetMapping("shows/showId/{id}")
	public ResponseEntity<Show> viewShow(@PathVariable("id") int showId) throws ShowNotFoundException{
		return new ResponseEntity<>(showService.viewShow(showId),HttpStatus.OK);
	}

	// View all the shows
	// Get Mapping since a list of shows is to be returned
	@GetMapping("shows/all")
	public ResponseEntity<List<Show>> viewAllShows() throws ShowNotPresentException{
		return new ResponseEntity<>(showService.viewAllShows(),HttpStatus.OK);
	}

	// View all the shows having a particular theatre id
	// Get Mapping since a list of shows is to be returned
	@GetMapping("shows/theatreId/{id}")
	public ResponseEntity<List<Show>> viewShowList(@PathVariable("id")int theatreid) throws ShowNotFoundException{
		return new ResponseEntity<>(showService.viewShowList(theatreid),HttpStatus.OK);
	}

	// View all the shows on a particular date
	// Get Mapping since a list of shows is to be returned
	@GetMapping("shows/date/{date}")
	public ResponseEntity<List<Show>> viewShowList(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws ShowNotFoundException{
		return new ResponseEntity<>(showService.viewShowList(date),HttpStatus.OK);
	}
	
	////View the movie details for a show
	//Get Mapping since movie is to be returned
	@GetMapping("shows/movieDetails/{id}")
	public ResponseEntity<Movie> viewMovieDetails(@PathVariable("id") int showId) throws ShowNotFoundException {
		return new ResponseEntity<>(showService.viewMovieDetails(showId),HttpStatus.OK);
	}

}

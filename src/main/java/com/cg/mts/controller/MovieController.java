package com.cg.mts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.dto.MovieDTO;
import com.cg.mts.entities.Movie;
import com.cg.mts.service.IMovieService;
import com.cg.mts.utils.DTOEntityConverter;
import com.cg.mts.utils.EntityDTOConverter;

@RestController
public class MovieController {
	
	@Autowired
	IMovieService movieService;
	
	@Autowired
	DTOEntityConverter dtoEntityConverter;
	
	@Autowired
	EntityDTOConverter entityDtoConverter;
	
	@PostMapping("/movies")
	public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movieDto) {
		Movie newMovie = dtoEntityConverter.convertToMovieEntity(movieDto);
		Movie savedMovie = movieService.addMovie(newMovie);
		MovieDTO movie = entityDtoConverter.convertToMovieDTO(savedMovie);
		return new ResponseEntity<>(movie,HttpStatus.OK);
	}

}

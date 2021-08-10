package com.cg.mts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.Movie;
import com.cg.mts.repository.IMovieRepository;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	IMovieRepository movieRepo;
	
	@Override
	public Movie addMovie(Movie movie) {
		return movieRepo.save(movie);
	}
}

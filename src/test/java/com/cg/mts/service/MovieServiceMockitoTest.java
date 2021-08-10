package com.cg.mts.service;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.mts.entities.Movie;
import com.cg.mts.repository.IMovieRepository;

//Automatically initialize all objects annotated with @MockBean and @InjectMocks
@ExtendWith(SpringExtension.class)
class MovieServiceMockitoTest {
	@InjectMocks
	MovieServiceImpl movieService;

	@MockBean
	IMovieRepository movieRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddMovieTrue() {
		Movie movie1 = new Movie(3, "Hi", "comedy", "2", "English", "Short Movie");
		movieService.addMovie(movie1);
		Mockito.verify(movieRepo, times(1)).save(movie1);
	}
	
}

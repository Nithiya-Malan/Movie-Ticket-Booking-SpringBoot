package com.cg.mts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.mts.entities.Movie;
import com.cg.mts.entities.Show;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.exception.ShowNotPresentException;

@SpringBootTest
class ShowServiceTest {

	@Autowired
	IShowService showService;

	// Declaring required fields
	Movie movie1;
	Show show1;

	@BeforeEach
	void setUp() {
		// Movies
		movie1 = new Movie(1, "Hello", "comedy", "2", "English", "Short Movie");

		//Shows
		show1 = new Show(205, LocalDateTime.now(), LocalDateTime.of(2021, 9, 12, 10, 15, 45), "Show5", movie1, 101,1001);
	}
	
	/*
	
	// Test Case 1 : Add show
		@Test
		void testAddShow() {
			Show persistedShow = showService.addShow(show1);
			// Testing
			assertThat(persistedShow).isNotNull();
			assertEquals("Show5", persistedShow.getShowName());
		}

		// Test Case 2 : Delete Screen By Id;
		@Test
		void testDeleteShowById() throws ShowNotFoundException {
			Show deletedShow = showService.removeShow(1); 
			// Testing
			assertEquals("Show1", deletedShow.getShowName());
			assertEquals(movie1,deletedShow.getMovie());
		}

		
		// Test Case 3 : Update Screen
		@Test
		void testUpdateShow() throws ShowNotFoundException {
			show1 = new Show(2, LocalDateTime.now(), LocalDateTime.of(2021, 9, 12, 10, 15, 45), "ShowNew", movie1, 101,1001);
			Show updatedShow = showService.updateShow(show1);
			// Testing
			assertThat(updatedShow).isNotNull();
			assertEquals("ShowNew",updatedShow.getShowName());
		}

		// Test Case 4 : Get all the screens
		@Test
		void testGetAllShows() throws ShowNotPresentException {
			List<Show> shows = showService.viewAllShows();
			// Testing
			assertThat(shows).isNotNull();
			assertEquals(5, shows.size());
		}

*/
}

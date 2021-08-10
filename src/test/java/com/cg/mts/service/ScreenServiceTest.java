package com.cg.mts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.mts.entities.Movie;
import com.cg.mts.entities.Screen;
import com.cg.mts.entities.Show;

@SpringBootTest
class ScreenServiceTest {

	@Autowired
	IScreenService screenService;

	// Declaring required fields
	Movie movie1, movie2;
	Show show1, show2, show3, show4, show5;
	Screen screen2;
	List<Show> showList;

	@BeforeEach
	void setUp() {
		// Movies
		movie1 = new Movie(1, "Hello", "comedy", "2", "English", "Short Movie");
		movie2 = new Movie(2, "Demo", "action", "3", "English", "Long Movie");

		// List of shows
		showList = new ArrayList<>();

		// Screens
		screen2 = new Screen(102, 1002, "Screen 2", showList, 150, 300);

	}
	
	/*

	// Test Case 1 : Add screen
	@Test
	void testAddScreen() {
		Screen persistedScreen = screenService.addScreen(screen2);
		// Testing
		assertThat(persistedScreen).isNotNull();
		assertEquals("Screen 2", persistedScreen.getScreenName());
	}

	// Test Case 2 : Delete Screen By Id;

	
	@Test
	void testDeleteScreenById() {
		Screen deletedScreen = screenService.deleteScreenById(5); 
		// Testing
		assertEquals("Screen 2", deletedScreen.getScreenName());
		assertEquals(5, deletedScreen.getScreenId());
	}
	
	
	// Test Case 3 : Update Screen
	@Test
	void testUpdateScreen() {
		screen2 = new Screen(7, 1002, "Screen 2", showList, 500, 100);
		Screen updatedScreen = screenService.updateScreen(screen2);
		// Testing
		assertThat(updatedScreen).isNotNull();
		assertEquals(500, updatedScreen.getRows());
		assertEquals(100, updatedScreen.getColumns());
	}

	// Test Case 4 : Get all the screens
	@Test
	void testGetAllScreens() {
		List<Screen> screens = screenService.getAllScreens();
		// Testing
		assertThat(screens).isNotNull();
		assertEquals(8, screens.size());
	}
	
	*/
}

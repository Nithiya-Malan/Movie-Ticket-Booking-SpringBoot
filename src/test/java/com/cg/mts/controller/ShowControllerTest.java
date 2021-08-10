package com.cg.mts.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cg.mts.entities.Movie;
import com.cg.mts.entities.Show;
import com.cg.mts.service.IShowService;

@ExtendWith(SpringExtension.class)
public class ShowControllerTest {

private MockMvc mockMvc;
	
	@InjectMocks
	ShowController showController;
	
	@Mock
	IShowService showService;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(showController).build();
	}
	
	// Declaring required fields
		Movie movie1, movie2;
		Show show1, show2, show3, show4, show5;
		List<Show> showList;

		// Initialization of Mock Objects
		@BeforeEach
		void init() {
			MockitoAnnotations.openMocks(this);
		}

		@BeforeEach
		void setUp() {
			// Movies
			movie1 = new Movie(1, "Hello", "comedy", "2", "English", "Short Movie");
			movie2 = new Movie(2, "Demo", "action", "3", "English", "Long Movie");

			// List of shows
			showList = new ArrayList<>();

			// Shows
			show1 = new Show(201, LocalDateTime.now(), LocalDateTime.of(2021, 9, 12, 10, 15, 45), "Show1", movie1, 101,
					1001);
			show2 = new Show(202, LocalDateTime.now(), LocalDateTime.of(2021, 12, 12, 5, 15, 45), "Show2", movie2, 101,
					1001);
			show3 = new Show(203, LocalDateTime.now(), LocalDateTime.of(2021, 9, 10, 10, 15, 15), "Show3", movie1, 102,
					1002);
			show4 = new Show(201, LocalDateTime.now(), LocalDateTime.of(2021, 12, 12, 10, 15, 45), "Show4", movie1, 102,
					1002);
			show5 = new Show(201, LocalDateTime.now(), LocalDateTime.of(2021, 9, 12, 10, 15, 45), "Show5", movie2, 103,
					1003);

			// Adding shows to the list
			showList.add(show1);
			showList.add(show2);
			showList.add(show3);
			showList.add(show4);
			showList.add(show5);

		}
		
    // Test Case : Get All Shows
	@Test
	public void testGetAllShows() throws Exception {
		Mockito.when(showService.viewAllShows()).thenReturn(showList);
		mockMvc.perform(MockMvcRequestBuilders.get("/shows/all"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	// Test Case : Get Show By Id
	@Test
	public void testGetShowById() throws Exception {
		Mockito.when(showService.viewShow(202)).thenReturn(show2);
		mockMvc.perform(MockMvcRequestBuilders.get("/shows/202")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	// Test Case : Delete Screen By Id
	@Test
	public void testDeleteShow() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/shows/201")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}

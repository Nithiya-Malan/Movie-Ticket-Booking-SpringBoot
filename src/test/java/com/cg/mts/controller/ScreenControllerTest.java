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
import com.cg.mts.entities.Screen;
import com.cg.mts.entities.Show;
import com.cg.mts.service.IScreenService;

@ExtendWith(SpringExtension.class)
class ScreenControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	ScreenController screenController;
	
	@Mock
	IScreenService screenService;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(screenController).build();
	}
	
	// Declaring required fields
		Movie movie1, movie2;
		Show show1, show2, show3, show4, show5;
		Screen screen1, screen2, screen3;
		List<Show> showList1, showList2, showList3;
		List<Screen> screenList;

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
			showList1 = new ArrayList<>();
			showList2 = new ArrayList<>();
			showList3 = new ArrayList<>();

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
			showList1.add(show1);
			showList1.add(show2);
			showList2.add(show3);
			showList2.add(show4);
			showList3.add(show5);

			// Screens
			screen1 = new Screen(101, 1001, "Screen 1", showList1, 100, 200);
			screen2 = new Screen(102, 1002, "Screen 2", showList2, 150, 300);
			screen3 = new Screen(103, 1003, "Screen 3", showList3, 200, 300);

			// Adding screens to list
			screenList = new ArrayList<>();
			screenList.add(screen1);
			screenList.add(screen2);
			screenList.add(screen3);

		}
		
    // Test Case : Get All Screens
	@Test
	public void testGetAllScreens() throws Exception {
		Mockito.when(screenService.getAllScreens()).thenReturn(screenList);
		mockMvc.perform(MockMvcRequestBuilders.get("/screens/all"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	// Test Case : Get Screen By Id
	@Test
	public void testGetScreenById() throws Exception {
		Mockito.when(screenService.getScreenById(102)).thenReturn(screen2);
		mockMvc.perform(MockMvcRequestBuilders.get("/screens/102")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	// Test Case : Get Screen By Name
	@Test
	public void testGetScreenByName() throws Exception {
		Mockito.when(screenService.getScreenByName("Screen 1")).thenReturn(screen1);
		mockMvc.perform(MockMvcRequestBuilders.get("/screens/screenName/Screen 1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	// Test Case : Delete Screen By Id
	@Test
	public void testDeleteScreen() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/screens/101")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	
}

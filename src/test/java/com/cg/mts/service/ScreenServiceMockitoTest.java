package com.cg.mts.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.mts.entities.Movie;
import com.cg.mts.entities.Screen;
import com.cg.mts.entities.Show;
import com.cg.mts.exception.ScreenNotFoundException;
import com.cg.mts.exception.ScreenNotPresentException;
import com.cg.mts.repository.IScreenRepository;

//Automatically initialize all objects annotated with @MockBean and @InjectMocks
@ExtendWith(SpringExtension.class)
class ScreenServiceMockitoTest {

	@InjectMocks
	ScreenServiceImpl screenService;

	@MockBean
	IScreenRepository screenRepo;

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

	// Test Case 1: Get All Screens ( When screens are present)
	@Test
	public void testGetAllScreensTrue() {
		Mockito.when(screenRepo.findAll()).thenReturn(screenList);
		List<Screen> screens = screenService.getAllScreens();
		assertEquals(3, screens.size());
		assertEquals(screenList, screens);
		assertNotNull(screens);
	}

	// Test Case 2: Get All Screens ( When no screens are present)
	@Test
	public void testGetAllScreensFalse() {
		List<Screen> newScreens = new ArrayList<>();
		Mockito.when(screenRepo.findAll()).thenReturn(newScreens);
		assertThatThrownBy(() -> screenService.getAllScreens()).isInstanceOf(ScreenNotPresentException.class);
	}

	// Test Case 3 : Get Screen By Id ( When screen id is present)
	@Test
	public void testGetScreenByIdTrue() {
		Mockito.when(screenRepo.findById(101)).thenReturn(Optional.of(screen1));
		Screen screen = screenService.getScreenById(101);
		assertEquals(screen, screen1);
		assertNotNull(screen);
		assertEquals("Screen 1", screen.getScreenName());
	}

	// Test Case 4 : Get Screen By Id ( When screen id is not present)
	@Test
	public void testGetScreenByIdFalse() {
		Mockito.when(screenRepo.findById(101)).thenReturn(Optional.of(screen1));
		assertThatThrownBy(() -> screenService.getScreenById(1000)).isInstanceOf(ScreenNotFoundException.class);
	}

	// Test Case 5: Get Screen By Theatre Id ( When theatre id is present)
	@Test
	public void testGetScreenByTheatreIdTrue() {
		List<Screen> screens = new ArrayList<>();
		screens.add(screen2);
		Mockito.when(screenRepo.findByTheatreId(1002)).thenReturn(screens);
		List<Screen> screen = screenService.getScreenByTheatreId(1002);
		assertEquals(screens, screen);
		assertNotNull(screen);
		assertEquals(102, screen.get(0).getScreenId());
		assertEquals(1, screen.size());
	}

	// Test Case 6: Get Screen By Theatre Id( When theatre id is not present)
	@Test
	public void testGetScreenByTheatreIdFalse() {
		List<Screen> screens = new ArrayList<>();
		screens.add(screen2);
		Mockito.when(screenRepo.findByTheatreId(1002)).thenReturn(screens);
		assertThatThrownBy(() -> screenService.getScreenByTheatreId(100)).isInstanceOf(ScreenNotPresentException.class);
	}

	// Test Case 7 : Get screen by name (When screen name is present)
	@Test
	public void testGetScreenByNameTrue() {
		Mockito.when(screenRepo.findByScreenName("Screen 1")).thenReturn(screen1);
		Screen screen = screenService.getScreenByName("Screen 1");
		assertEquals(screen, screen1);
		assertEquals(1001, screen.getTheatreId());
		assertNotNull(screen);
	}

	// Test Case 8 : Get screen by name (When screen name is not present)
	@Test
	public void testGetScreenByNameFalse() {
		Mockito.when(screenRepo.findByScreenName("Screen 1")).thenReturn(screen1);
		Mockito.when(screenRepo.findByScreenName("Screen 2")).thenReturn(screen2);
		assertThatThrownBy(() -> screenService.getScreenByName("Screen 3")).isInstanceOf(ScreenNotFoundException.class);
	}

	// Test Case 9 : Add Screen
	@Test
	public void testAddScreenTrue() {
		screenService.addScreen(screen1);
		Mockito.verify(screenRepo, times(1)).save(screen1);
	}

	// Test Case 10 : Delete Screen By Id (When the screen id is present)
	@Test
	public void testDeleteScreenTrue() {
		Mockito.when(screenRepo.findById(101)).thenReturn(Optional.of(screen1));
		Screen screen = screenService.deleteScreenById(101);
		Mockito.verify(screenRepo).deleteById(screen.getScreenId());
	}

	// Test Case 11 : Delete Screen By Id (When the screen id is not present)
	@Test
	public void testDeleteScreenFalse() {
		Mockito.when(screenRepo.findById(101)).thenReturn(Optional.of(screen1));
		assertThatThrownBy(() -> screenService.deleteScreenById(109)).isInstanceOf(ScreenNotFoundException.class);
	}

	// Test Case 12 : Update Screen (When the screen id is present)
	@Test
	public void testUpdateScreenTrue() {
		Screen newScreen = new Screen(101, 1001, "New Screen", showList3, 10, 20);
		Mockito.when(screenRepo.findById(newScreen.getScreenId())).thenReturn(Optional.of(screen1));
		Screen screen = screenService.updateScreen(newScreen);
		assertNotNull(screen);
		assertEquals("New Screen", screen.getScreenName());
	}

	// Test Case 13 : Update Screen (When the screen id is not present)
	@Test
	public void testUpdateScreenFalse() {
		Screen newScreen = new Screen(1001, 1001, "New Screen", showList3, 10, 20);
		assertThatThrownBy(() -> screenService.updateScreen(newScreen)).isInstanceOf(ScreenNotFoundException.class);
	}

	// Test Case 14 : Update Screen Name(When the screen id is present)
	@Test
	public void testUpdateScreenNameTrue() {
		String screenName = "Screen Name Updated";
		Mockito.when(screenRepo.findById(101)).thenReturn(Optional.of(screen1));
		Screen screen = screenService.updateScreenName(101, screenName);
		assertNotNull(screen);
		assertEquals("Screen Name Updated", screen.getScreenName());
	}

	// Test Case 15 : Update Screen Name(When the screen id is not present)
	@Test
	public void testUpdateScreenNameFalse() {
		String screenName = "Screen Name Updated";
		Mockito.when(screenRepo.findById(101)).thenReturn(Optional.of(screen1));
		assertThatThrownBy(() -> screenService.updateScreenName(10000, screenName))
				.isInstanceOf(ScreenNotFoundException.class);
	}

	// Test Case 16 : Update Screen Rows(When the screen id is present)
	@Test
	public void testUpdateScreenRowsTrue() {
		int rows = 25;
		Mockito.when(screenRepo.findById(101)).thenReturn(Optional.of(screen1));
		Screen screen = screenService.updateScreenRows(101, rows);
		assertNotNull(screen);
		assertEquals(rows, screen.getRows());
	}

	// Test Case 17 : Update Screen Rows(When the screen id is not present)
	@Test
	public void testUpdateScreenRowsFalse() {
		int rows = 25;
		Mockito.when(screenRepo.findById(101)).thenReturn(Optional.of(screen1));
		assertThatThrownBy(() -> screenService.updateScreenRows(10000, rows))
				.isInstanceOf(ScreenNotFoundException.class);
	}

	// Test Case 18 : Update Screen Columns(When the screen id is present)
	@Test
	public void testUpdateScreenColumnsTrue() {
		int cols = 25;
		Mockito.when(screenRepo.findById(101)).thenReturn(Optional.of(screen1));
		Screen screen = screenService.updateScreenColumns(101, cols);
		assertNotNull(screen);
		assertEquals(cols, screen.getColumns());
	}

	// Test Case 19 : Update Screen Columns(When the screen id is not present)
	@Test
	public void testUpdateScreenColumnsFalse() {
		int cols = 25;
		Mockito.when(screenRepo.findById(101)).thenReturn(Optional.of(screen1));
		assertThatThrownBy(() -> screenService.updateScreenColumns(10000, cols))
				.isInstanceOf(ScreenNotFoundException.class);
	}
}

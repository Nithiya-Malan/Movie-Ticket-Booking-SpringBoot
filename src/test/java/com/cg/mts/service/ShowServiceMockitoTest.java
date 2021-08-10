package com.cg.mts.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
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
import com.cg.mts.entities.Show;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.exception.ShowNotPresentException;
import com.cg.mts.repository.IShowRepository;

//Automatically initialize all objects annotated with @MockBean and @InjectMocks
@ExtendWith(SpringExtension.class)
class ShowServiceMockitoTest {

	@InjectMocks
	ShowServiceImpl showService;

	@MockBean
	IShowRepository showRepo;
	
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

		// Shows
		show1 = new Show(201, LocalDateTime.now(), LocalDateTime.of(2021, 9, 12, 10, 15, 45), "Show1", movie1, 101,
				1001);
		show2 = new Show(202, LocalDateTime.now(), LocalDateTime.of(2021, 12, 12, 5, 15, 45), "Show2", movie2, 101,
				1001);
		show3 = new Show(203, LocalDateTime.now(), LocalDateTime.of(2021, 9, 10, 10, 15, 15), "Show3", movie1, 102,
				1002);
		show4 = new Show(204, LocalDateTime.now(), LocalDateTime.of(2021, 12, 12, 10, 15, 45), "Show4", movie1, 102,
				1002);
		show5 = new Show(205, LocalDateTime.now(), LocalDateTime.of(2021, 9, 12, 10, 15, 45), "Show5", movie2, 103,
				1003);

		// Adding shows to the list
		showList = new ArrayList<>();
		showList.add(show1);
		showList.add(show2);
		showList.add(show3);
		showList.add(show4);
		showList.add(show5);
	}

	// Test Case 1 : Add Show
	@Test
	public void testAddShow() {
		showService.addShow(show1);
		Mockito.verify(showRepo, times(1)).save(show1);
	}

	// Test Case 2 : Update Show (When the show id is present)
	@Test
	public void testUpdateShowTrue() throws ShowNotFoundException {
		Show newShow = new Show(205, LocalDateTime.now(), LocalDateTime.of(2021, 12, 12, 5, 15, 45), "New Show", movie1,
				101, 1003);
		Mockito.when(showRepo.findById(newShow.getShowId())).thenReturn(Optional.of(show5));
		Show show = showService.updateShow(newShow);

		assertNotNull(show);
		assertEquals("New Show", show.getShowName());
		assertEquals(1003, show.getTheatreId());
		assertEquals(movie1, show.getMovie());
	}

	// Test Case 3 : Update Show (When the show id is not present)
	@Test
	public void testUpdateShowFalse() {
		Show newShow = new Show(20000, LocalDateTime.now(), LocalDateTime.of(2021, 12, 12, 5, 15, 45), "New Show",
				movie1, 101, 1003);
		assertThatThrownBy(() -> showService.updateShow(newShow)).isInstanceOf(ShowNotFoundException.class);
	}

	// Test Case 4 : Delete Show (When the show id is present)
	@Test
	public void testRemoveShowTrue() throws ShowNotFoundException {
		Mockito.when(showRepo.findById(204)).thenReturn(Optional.of(show4));
		Show show = showService.removeShow(204);
		Mockito.verify(showRepo).deleteById(show.getShowId());
	}

	// Test Case 5 : Delete Show (When the show id is not present)
	@Test
	public void testRemoveShowFalse() throws ShowNotFoundException {
		Mockito.when(showRepo.findById(204)).thenReturn(Optional.of(show4));
		assertThatThrownBy(() -> showService.removeShow(210)).isInstanceOf(ShowNotFoundException.class);
	}

	// Test Case 6 : View a Show by Show Id (When the show id is present)
	@Test
	public void testViewShowByIdTrue() throws ShowNotFoundException {
		Mockito.when(showRepo.findById(203)).thenReturn(Optional.of(show3));
		Show show = showService.viewShow(203);
		assertEquals(show3, show);
		assertNotNull(show);
		assertEquals("Show3", show.getShowName());
	}

	// Test Case 7 : View a Show by Show Id (When the show id is not present)
	@Test
	public void testViewShowByIdFalse() throws ShowNotFoundException {
		Mockito.when(showRepo.findById(203)).thenReturn(Optional.of(show3));
		assertThatThrownBy(() -> showService.viewShow(209)).isInstanceOf(ShowNotFoundException.class);
	}

	// Test Case 8 : View All Shows (When the show id is present)
	@Test
	public void testViewAllShowsTrue() throws ShowNotPresentException {
		Mockito.when(showRepo.findAll()).thenReturn(showList);
		List<Show> shows = showService.viewAllShows();
		assertEquals(5, shows.size());
		assertEquals(showList, shows);
		assertNotNull(shows);
	}

	// Test Case 9 : View All Shows (When the show id is not present)
	@Test
	public void testViewAllShowsFalse() throws ShowNotPresentException {
		List<Show> newShows = new ArrayList<>();
		Mockito.when(showRepo.findAll()).thenReturn(newShows);
		assertThatThrownBy(() -> showService.viewAllShows()).isInstanceOf(ShowNotPresentException.class);
	}

	// Test Case 10 : View Shows by TheatreId (When theatreId is present)
	@Test
	public void testViewShowsByTheatreIdTrue() throws ShowNotFoundException {
		List<Show> shows = new ArrayList<>();
		shows.add(show3);
		shows.add(show4);
		Mockito.when(showRepo.findByTheatreId(1002)).thenReturn(shows);
		List<Show> show = showService.viewShowList(1002);
		assertEquals(shows, show);
		assertNotNull(show);
		assertEquals(102, show.get(0).getScreenId());
		assertEquals(2, show.size());
	}

	// Test Case 11 : View Shows by TheatreId (When theatreId is not present)
	@Test
	public void testViewShowsByTheatreIdFalse() {
		List<Show> shows = new ArrayList<>();
		shows.add(show3);
		shows.add(show4);
		Mockito.when(showRepo.findByTheatreId(1002)).thenReturn(shows);
		assertThatThrownBy(() -> showService.viewShowList(100)).isInstanceOf(ShowNotFoundException.class);
	}

	// Test Case 12 : View Shows by Date (When date is present)
	@Test
	public void testViewShowsByDateTrue() throws ShowNotFoundException {
		List<Show> shows = new ArrayList<>();
		shows.add(show1);
		shows.add(show5);
		Mockito.when(showRepo.viewShowList(LocalDate.of(2021, 9, 12))).thenReturn(shows);
		List<Show> show = showService.viewShowList(LocalDate.of(2021, 9, 12));
		assertEquals(shows, show);
		assertNotNull(show);
		assertEquals("Show1", show.get(0).getShowName());
		assertEquals(2, show.size());
	}

	// Test Case 13 : View Shows by Date (When date is not present)
	@Test
	public void testViewShowsByDateFalse() throws ShowNotFoundException {
		List<Show> shows = new ArrayList<>();
		shows.add(show1);
		shows.add(show5);
		Mockito.when(showRepo.viewShowList(LocalDate.of(2021, 9, 12))).thenReturn(shows);
		assertThatThrownBy(() -> showService.viewShowList(LocalDate.of(2021, 12, 12)))
				.isInstanceOf(ShowNotFoundException.class);
	}
	
	// Test Case 14 : View Movie Details by showId ( When showId is present)
	@Test
	public void testViewMovieDetailsTrue() throws ShowNotFoundException {
		Mockito.when(showRepo.findById(202)).thenReturn(Optional.of(show2));
		Movie movie = showService.viewMovieDetails(202);
		assertEquals(movie2,movie);
		assertNotNull(movie);
		assertEquals("English",movie.getMovieLanguage());
		assertEquals("Demo",movie.getMovieName());
		assertEquals("3",movie.getMovieHours());
	}

	// Test Case 15 : View Movie Details by showId ( When showId is not present)
	@Test
	public void testViewMovieDetailsFalse() throws ShowNotFoundException {
		Mockito.when(showRepo.findById(202)).thenReturn(Optional.of(show2));
		assertThatThrownBy(() -> showService.viewMovieDetails(210))
		.isInstanceOf(ShowNotFoundException.class);
	}
}

package com.cg.mts.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.mts.dto.MovieDTO;
import com.cg.mts.dto.ScreenDTO;
import com.cg.mts.dto.ShowDTO;
import com.cg.mts.entities.Movie;
import com.cg.mts.entities.Screen;
import com.cg.mts.entities.Show;

@Component
public class DTOEntityConverter {
	
	//Conversion from ScreenDTO to Screen
	public Screen convertToScreenEntity(ScreenDTO screenDto){
		Screen screen = new Screen();
		screen.setScreenId(screenDto.getScreenId());
		screen.setTheatreId(screenDto.getTheatreId());
		screen.setScreenName(screenDto.getScreenName());
		screen.setRows(screenDto.getRows());
		screen.setColumns(screenDto.getColumns());
		List<ShowDTO> showDtoList = screenDto.getShowList();
		List<Show> showList = new ArrayList<>();
		for(ShowDTO showDto : showDtoList) {
			showList.add(convertToShowEntity(showDto));
		}
		screen.setShowList(showList);
		return screen;
	}
	
	//Conversion from ShowDTO to Show
	public Show convertToShowEntity(ShowDTO showDto) {
		Show show = new Show();
		show.setShowId(showDto.getShowId());
		show.setShowStartTime(showDto.getShowStartTime());
		show.setShowEndTime(showDto.getShowEndTime());
		show.setShowName(showDto.getShowName());
		show.setMovie(showDto.getMovie());
		show.setScreenId(showDto.getScreenId());
		show.setTheatreId(showDto.getTheatreId());
		return show;
	}
	
	//Conversion from MovieDTO to Movie
	public Movie convertToMovieEntity(MovieDTO movieDto) {
		Movie movie = new Movie();
		movie.setMovieId(movieDto.getMovieId());
		movie.setMovieName(movieDto.getMovieName());
		movie.setMovieDescription(movieDto.getMovieDescription());
		movie.setMovieLanguage(movieDto.getMovieLanguage());
		movie.setMovieGenre(movieDto.getMovieGenre());
		movie.setMovieHours(movieDto.getMovieHours());
		return movie;
	}

}

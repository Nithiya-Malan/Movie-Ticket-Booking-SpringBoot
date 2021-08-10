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
public class EntityDTOConverter {
	
	//Conversion from Screen to ScreenDTO
		public ScreenDTO convertToScreenDTO(Screen screen){
			ScreenDTO screenDto = new ScreenDTO();
			screenDto.setScreenId(screen.getScreenId());
			screenDto.setTheatreId(screen.getTheatreId());
			screenDto.setScreenName(screen.getScreenName());
			screenDto.setRows(screen.getRows());
			screenDto.setColumns(screen.getColumns());
			List<Show> showList = screen.getShowList();
			List<ShowDTO> showDtoList = new ArrayList<>();
			for(Show show : showList) {
				showDtoList.add(convertToShowDTO(show));
			}
			screenDto.setShowList(showDtoList);
			return screenDto;
		}
		
		//Conversion from Show to ShowDTO
		public ShowDTO convertToShowDTO(Show show) {
			ShowDTO showDto = new ShowDTO();
			showDto.setShowId(show.getShowId());
			showDto.setShowStartTime(show.getShowStartTime());
			showDto.setShowEndTime(show.getShowEndTime());
			showDto.setShowName(show.getShowName());
			showDto.setMovie(show.getMovie());
			showDto.setScreenId(show.getScreenId());
			showDto.setTheatreId(show.getTheatreId());
			return showDto;
		}
		
		//Conversion from Movie to MovieDTO
		public MovieDTO convertToMovieDTO(Movie movie) {
			MovieDTO movieDto = new MovieDTO();
			movieDto.setMovieId(movie.getMovieId());
			movieDto.setMovieName(movie.getMovieName());
			movieDto.setMovieDescription(movie.getMovieDescription());
			movieDto.setMovieLanguage(movie.getMovieLanguage());
			movieDto.setMovieGenre(movie.getMovieGenre());
			movieDto.setMovieHours(movie.getMovieHours());
			return movieDto;
		}

}

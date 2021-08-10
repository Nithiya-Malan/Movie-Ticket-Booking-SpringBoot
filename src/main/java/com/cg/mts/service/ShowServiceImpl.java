package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.Movie;
import com.cg.mts.entities.Show;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.exception.ShowNotPresentException;
import com.cg.mts.repository.IShowRepository;

@Service
public class ShowServiceImpl implements IShowService {

	@Autowired
	IShowRepository showRepo;
	
	String message = "Show Not Found.Invalid Show Id : ";
	
	@Override
	public Show addShow(Show show){
		return showRepo.save(show);
	}

	@Override
	public Show updateShow(Show show) throws ShowNotFoundException {
		Optional<Show> showUpdate = showRepo.findById(show.getShowId());
		if(!showUpdate.isPresent()) {
			throw new ShowNotFoundException(message + show.getShowId());
		}
		Show dbShow = showUpdate.get();
		dbShow.setShowStartTime(show.getShowStartTime());
		dbShow.setShowEndTime(show.getShowEndTime());
		dbShow.setShowName(show.getShowName());
		dbShow.setMovie(show.getMovie());
		dbShow.setScreenId(show.getScreenId());
		dbShow.setTheatreId(show.getTheatreId());
		showRepo.save(dbShow);
		return show;
	}

	@Override
	public Show removeShow(int showId) throws ShowNotFoundException {
		Optional<Show> show = showRepo.findById(showId);
		if(!show.isPresent()) {
			throw new ShowNotFoundException(message + showId);
		}
		showRepo.deleteById(showId);
		return show.get();
	}

	@Override
	public Show viewShow(int showId) throws ShowNotFoundException {
		Optional<Show> show = showRepo.findById(showId);
		if(!show.isPresent()) {
			throw new ShowNotFoundException(message + showId);
		}
		return show.get();
	}

	@Override
	public List<Show> viewAllShows() throws ShowNotPresentException {
		List<Show> showList = showRepo.findAll();
		if(showList.isEmpty()) {
			throw new ShowNotPresentException("Shows are not present");
		}
		return showList;
	}

	@Override
	public List<Show> viewShowList(int theatreId) throws ShowNotFoundException {
		List<Show> showList = showRepo.findByTheatreId(theatreId);
		if(showList.isEmpty()) {
			throw new ShowNotFoundException("Shows are not present . Please enter a valid theatre id");
		}
		return showList;
	}

	@Override
	public List<Show> viewShowList(LocalDate date) throws ShowNotFoundException {
		List<Show> showList = showRepo.viewShowList(date);
		if(showList.isEmpty()) {
			throw new ShowNotFoundException("Shows are not present . Please enter another date");
		}
		return showList;
	}

	@Override
	public Movie viewMovieDetails(int showId) throws ShowNotFoundException {
		Optional<Show> show = showRepo.findById(showId);
		if(!show.isPresent()) {
			throw new ShowNotFoundException("Show not present . Please enter a valid id");
		}
		return show.get().getMovie();
	}

}

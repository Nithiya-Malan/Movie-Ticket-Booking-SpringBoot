package com.cg.mts.dto;

import java.time.LocalDateTime;

import com.cg.mts.entities.Movie;

public class ShowDTO {
	
	//Fields
	private int showId;
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;
	private String showName;
	private Movie movie;
	private int screenId;
	private int theatreId;
	
	//Default Constructor
	public ShowDTO() {}

	//Parameterized Constructor
	public ShowDTO(int showId, LocalDateTime showStartTime, LocalDateTime showEndTime, String showName, Movie movie,
			int screenId, int theatreId) {
		super();
		this.showId = showId;
		this.showStartTime = showStartTime;
		this.showEndTime = showEndTime;
		this.showName = showName;
		this.movie = movie;
		this.screenId = screenId;
		this.theatreId = theatreId;
	}

	//Getters and Setters
	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public LocalDateTime getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(LocalDateTime showStartTime) {
		this.showStartTime = showStartTime;
	}

	public LocalDateTime getShowEndTime() {
		return showEndTime;
	}

	public void setShowEndTime(LocalDateTime showEndTime) {
		this.showEndTime = showEndTime;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	//toString() method
	@Override
	public String toString() {
		return "ShowDTO [showId=" + showId + ", showStartTime=" + showStartTime + ", showEndTime=" + showEndTime
				+ ", showName=" + showName + ", movie=" + movie + ", screenId=" + screenId + ", theatreId=" + theatreId
				+ "]";
	}

}

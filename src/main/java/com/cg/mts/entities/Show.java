package com.cg.mts.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Show {
	
	//Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int showId;
	
	@Column(name = "start_time")
	private LocalDateTime showStartTime;
	
	@Column(name = "end_time")
	private LocalDateTime showEndTime;
	
	@NotEmpty(message = "Show name should not be empty.")
	@Size(min = 5, message = "Show name must have atleast 5 characters.")
	private String showName;
	
	@OneToOne(cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
    private Movie movie;
    
    @NotNull(message = "Screen Id should not be null.")
	@Column(name = "screen_id")
	private int screenId;
	
	@NotNull(message = "Theatre Id should not be null.")
	@Min(3)
	@Column(name = "theatre_id")
	private int theatreId;
	
	//Default Constructor
	public Show() {}

	//Parameterized Constructor
	public Show(int showId, LocalDateTime showStartTime, LocalDateTime showEndTime,
			@NotEmpty(message = "Show name should not be empty.") @Size(min = 5, message = "Show name must have atleast 5 characters.") String showName,
			Movie movie,
			@NotNull(message = "Screen Id should not be null.") @Size(min = 3, message = "Screen Id must have atleast 3 digits.") int screenId,
			@NotNull(message = "Theatre Id should not be null.") @Size(min = 3, message = "Theatre Id must have atleast 3 digits.") int theatreId) {
		super();
		this.showId = showId;
		this.showStartTime = showStartTime;
		this.showEndTime = showEndTime;
		this.showName = showName;
		this.movie = movie;
		this.screenId = screenId;
		this.theatreId = theatreId;
	}

	//Getters
	public int getShowId() {
		return showId;
	}

	public LocalDateTime getShowStartTime() {
		return showStartTime;
	}

	public LocalDateTime getShowEndTime() {
		return showEndTime;
	}

	public String getShowName() {
		return showName;
	}

	public Movie getMovie() {
		return movie;
	}

	public int getScreenId() {
		return screenId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	//Setters
	public void setShowId(int showId) {
		this.showId = showId;
	}

	public void setShowStartTime(LocalDateTime showStartTime) {
		this.showStartTime = showStartTime;
	}

	public void setShowEndTime(LocalDateTime showEndTime) {
		this.showEndTime = showEndTime;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	//toString() method to print the object
	@Override
	public String toString() {
		return "Show [showId=" + showId + ", showStartTime=" + showStartTime + ", showEndTime=" + showEndTime
				+ ", showName=" + showName + ", movie=" + movie + ", screenId=" + screenId + ", theatreId=" + theatreId
				+ "]";
	}

}

package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie {

	//Fields
	@Id
	private int movieId;
	private String movieName;
	private String movieGenre;
	private String movieHours;
	private String movieLanguage;
	private String movieDescription;
	
	//Default COnstructor
	public Movie() {}

	//Parameterized Constructor
	public Movie(int movieId, String movieName, String movieGenre, String movieHours, String movieLanguage,
			String movieDescription) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieHours = movieHours;
		this.movieLanguage = movieLanguage;
		this.movieDescription = movieDescription;
	}

	//Getters
	public int getMovieId() {
		return movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public String getMovieHours() {
		return movieHours;
	}

	public String getMovieLanguage() {
		return movieLanguage;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	//Setters
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public void setMovieHours(String movieHours) {
		this.movieHours = movieHours;
	}

	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	//toString() method to print objects
	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", movieGenre=" + movieGenre + ", movieHours="
				+ movieHours + ", movieLanguage=" + movieLanguage + ", movieDescription=" + movieDescription + "]";
	}
	
	
	
}

package com.cg.mts.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Screen {
	
	//Fields 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int screenId;
	
	@NotNull(message = "Theatre Id should not be null.")
	@Min(3)
	@Column(name = "theatre_id")
	private int theatreId;
	
	@NotEmpty(message = "Screen name should not be empty.")
	@Size(min = 5, message = "Screen name must have atleast 5 characters.")
	@Column(name = "screen_name")
	private String screenName;
	
	@ManyToMany
	private List<Show> showList;
	
	@NotNull(message = "Number of rows should not be null.")
	private int rows;
	
	@NotNull(message = "Number of columns should not be null.")
	private int columns;
	
	//Default Constructor
	public Screen() {}

	//Parameterized Constructor
	public Screen(int screenId, int theatreId, String screenName, List<Show> showList, int rows, int columns) {
		super();
		this.screenId = screenId;
		this.theatreId = theatreId;
		this.screenName = screenName;
		this.showList = showList;
		this.rows = rows;
		this.columns = columns;
	}

	//Getters
	public int getScreenId() {
		return screenId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public String getScreenName() {
		return screenName;
	}

	public List<Show> getShowList() {
		return showList;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	//Setters
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public void setShowList(List<Show> showList) {
		this.showList = showList;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	//toString() method to print the object
	@Override
	public String toString() {
		return "Screen [screenId=" + screenId + ", theatreId=" + theatreId + ", screenName=" + screenName
				+ ", showList=" + showList + ", rows=" + rows + ", columns=" + columns + "]";
	}
	
}

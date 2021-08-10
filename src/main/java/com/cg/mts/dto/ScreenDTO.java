package com.cg.mts.dto;

import java.util.List;

public class ScreenDTO {
	
	//Fields
	private int screenId;
	private int theatreId;
	private String screenName;
	private List<ShowDTO> showList;
	private int rows;
	private int columns;
	
	//Default Constructor
	public ScreenDTO() {
	}

	//Parameterized Constructor
	public ScreenDTO(int screenId, int theatreId, String screenName, List<ShowDTO> showList, int rows, int columns) {
		super();
		this.screenId = screenId;
		this.theatreId = theatreId;
		this.screenName = screenName;
		this.showList = showList;
		this.rows = rows;
		this.columns = columns;
	}

	//Getters and Setters
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

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public List<ShowDTO> getShowList() {
		return showList;
	}

	public void setShowList(List<ShowDTO> showList) {
		this.showList = showList;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	//toString() method to print object
	@Override
	public String toString() {
		return "ScreenDTO [screenId=" + screenId + ", theatreId=" + theatreId + ", screenName=" + screenName
				+ ", showList=" + showList + ", rows=" + rows + ", columns=" + columns + "]";
	}

	
}

package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Screen;

public interface IScreenService {

	// Add a screen
	Screen addScreen(Screen screen);

	// Delete a screen based on the screen id
	Screen deleteScreenById(int screenId);

	// Update a screen
	Screen updateScreen(Screen screen);

	// Get a list of all screens available
	List<Screen> getAllScreens();

	// Get the screen based on the screen id
	Screen getScreenById(int screenId);

	// Get the screens based on the theatre id
	List<Screen> getScreenByTheatreId(int theatreId);

	// Get the screens based on the screen name
	Screen getScreenByName(String screenName);

	// Update a screen name based on screen id
	Screen updateScreenName(int screenId, String screenName);

	// Update the number of rows based on screen id
	Screen updateScreenRows(int screenId, int rows);

	// Update the number of columns based on screen id
	Screen updateScreenColumns(int screenId, int columns);
}

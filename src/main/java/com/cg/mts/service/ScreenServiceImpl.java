package com.cg.mts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.Screen;
import com.cg.mts.exception.ScreenNotFoundException;
import com.cg.mts.exception.ScreenNotPresentException;
import com.cg.mts.repository.IScreenRepository;

@Service
public class ScreenServiceImpl implements IScreenService {

	@Autowired
	IScreenRepository screenRepo; // instance of DAO layer
	
	String message = "Invalid Screen Id : ";

	// Add a screen
	@Override
	public Screen addScreen(Screen screen){
		return screenRepo.save(screen);
	}

	// Delete a screen based on the screen id
	@Override
	public Screen deleteScreenById(int screenId) {
		Optional<Screen> screen = screenRepo.findById(screenId);
		if(!screen.isPresent()) {
			throw new ScreenNotFoundException(message + screenId);
		}
		screenRepo.deleteById(screenId);
		return screen.get();
	}

	// Update a screen
	@Override
	public Screen updateScreen(Screen screen) {
		// Find the screen
		Optional<Screen> updScreen = screenRepo.findById(screen.getScreenId());
		// If the screen is not present,throw exception else update screen
		if (!updScreen.isPresent()) {
			throw new ScreenNotFoundException("Screen Not Found . Please enter the correct id.");
		}
		Screen dbScreen = updScreen.get();
		dbScreen.setTheatreId(screen.getTheatreId());
		dbScreen.setScreenName(screen.getScreenName());
		dbScreen.setShowList(screen.getShowList());
		dbScreen.setRows(screen.getRows());
		dbScreen.setColumns(screen.getColumns());
		screenRepo.save(dbScreen);
		return screen;
	}

	// Get a list of all screens available
	@Override
	public List<Screen> getAllScreens() {
		List<Screen> screenList = screenRepo.findAll();
		if (screenList.isEmpty()) {
			throw new ScreenNotPresentException("No screens available.");
		}
		return screenList;
	}

	// Get the screen based on the screen id
	@Override
	public Screen getScreenById(int screenId) {
		Optional<Screen> opt = screenRepo.findById(screenId);
		if (!opt.isPresent()) {
			throw new ScreenNotFoundException("Screen Not Found . Please enter the correct id.");
		}
		return opt.get();
	}

	// Get the screens based on the Theatre id
	@Override
	public List<Screen> getScreenByTheatreId(int theatreId) {
		List<Screen> screenList = screenRepo.findByTheatreId(theatreId);
		if (screenList.isEmpty()) {
			throw new ScreenNotPresentException("No screens available. Please enter a valid theatre id");
		}
		return screenList;
	}

	// Get the screens based on the screen name
	@Override
	public Screen getScreenByName(String screenName) {
		Screen screen = screenRepo.findByScreenName(screenName);
		if (screen == null) {
			throw new ScreenNotFoundException("Invalid Screen Name : " + screenName);
		}
		return screen;
	}

	@Override
	public Screen updateScreenName(int screenId, String screenName) {
		// Find the screen
		Optional<Screen> updScreen = screenRepo.findById(screenId);
		// If the screen is present,update the screen name
		if (!updScreen.isPresent()) {
			throw new ScreenNotFoundException(message + screenId);
		}
		Screen dbScreen = updScreen.get();
		dbScreen.setScreenName(screenName);
		screenRepo.save(dbScreen);
		return dbScreen;
	}

	@Override
	public Screen updateScreenRows(int screenId, int rows) {
		// Find the screen
		Optional<Screen> updScreen = screenRepo.findById(screenId);
		// If the screen is present,update the screen rows
		if (!updScreen.isPresent()) {
			throw new ScreenNotFoundException(message + screenId);
		}
		Screen dbScreen = updScreen.get();
		dbScreen.setRows(rows);
		screenRepo.save(dbScreen);
		return dbScreen;
	}

	@Override
	public Screen updateScreenColumns(int screenId, int columns) {
		// Find the screen
		Optional<Screen> updScreen = screenRepo.findById(screenId);
		// If the screen is present,update the screen columns
		if (!updScreen.isPresent()) {
			throw new ScreenNotFoundException(message + screenId);
		}
		Screen dbScreen = updScreen.get();
		dbScreen.setColumns(columns);
		screenRepo.save(dbScreen);
		return dbScreen;
	}

}

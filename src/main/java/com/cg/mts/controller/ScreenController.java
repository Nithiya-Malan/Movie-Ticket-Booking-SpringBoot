package com.cg.mts.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.dto.ScreenDTO;
import com.cg.mts.entities.Screen;
import com.cg.mts.service.IScreenService;
import com.cg.mts.utils.DTOEntityConverter;
import com.cg.mts.utils.EntityDTOConverter;

@RestController
public class ScreenController {

	@Autowired
	IScreenService screenService;
	
	@Autowired
	DTOEntityConverter dtoEntityConverter;
	
	@Autowired
	EntityDTOConverter entityDtoConverter;
	

	// Add a screen
	// Post Mapping since a screen is to be added
	@PostMapping("/screens")
	public ResponseEntity<ScreenDTO> addScreen(@Valid @RequestBody ScreenDTO screendto) {
		Screen newScreen = dtoEntityConverter.convertToScreenEntity(screendto);
		Screen savedScreen = screenService.addScreen(newScreen);
		ScreenDTO savedScreenDto = entityDtoConverter.convertToScreenDTO(savedScreen);
		return new ResponseEntity<>(savedScreenDto, HttpStatus.OK);
	}

	// Delete a screen based on the screen id
	// Delete Mapping since a screen is to be deleted
	@DeleteMapping("/screens/{id}")
	public ResponseEntity<Screen> deleteScreenById(@PathVariable("id") int screenId) {
		Screen screen = screenService.deleteScreenById(screenId);
		return new ResponseEntity<>(screen, HttpStatus.OK);
	}

	// Update a screen
	// Put Mapping since the entire screen data is to be updated
	@PutMapping("/screens")
	public ResponseEntity<ScreenDTO> updateScreen(@Valid @RequestBody ScreenDTO screenDto) {
		Screen newScreen = dtoEntityConverter.convertToScreenEntity(screenDto);
		Screen savedScreen = screenService.updateScreen(newScreen);
		ScreenDTO savedScreenDto = entityDtoConverter.convertToScreenDTO(savedScreen);
		return new ResponseEntity<>(savedScreenDto, HttpStatus.OK);
	}

	// Get a list of all screens available
	// Get Mapping since a list of screens is to be returned
	@GetMapping("/screens/all")
	public ResponseEntity<List<Screen>> getAllScreens() {
		return new ResponseEntity<>(screenService.getAllScreens(), HttpStatus.OK);
	}

	// Get the screen based on the screen id
	// Get Mapping since a screen is to be returned
	@GetMapping("/screens/{id}")
	public ResponseEntity<Screen> getScreenById(@PathVariable("id") int screenId) {
		return new ResponseEntity<>(screenService.getScreenById(screenId), HttpStatus.OK);
	}

	// Get the screens based on the theatre id
	@GetMapping("/screens/theatreId/{id}")
	// Get Mapping since a screen is to be returned
	public ResponseEntity<List<Screen>> getScreenByTheatreId(@PathVariable("id") int theatreId) {
		return new ResponseEntity<>(screenService.getScreenByTheatreId(theatreId), HttpStatus.OK);
	}

	// Get the screens based on the screen name
	// Get Mapping since a screen is to be returned
	@GetMapping("/screens/screenName/{name}")
	public ResponseEntity<Screen> getScreenByName(@PathVariable("name") String screenName) {
		return new ResponseEntity<>(screenService.getScreenByName(screenName), HttpStatus.OK);
	}

	// Update a screen name based on screen id
	// Patch Mapping since the only screen name is to be updated
	@PatchMapping("/screens/screenName/{id}")
	public ResponseEntity<Screen> updateScreenName(@Valid @PathVariable("id") int screenId,
			@RequestBody String screenName) {
		Screen screenUpdate = screenService.updateScreenName(screenId, screenName);
		return new ResponseEntity<>(screenUpdate, HttpStatus.OK);
	}

	// Update the number of rows based on screen id
	// Patch Mapping since the only rows is to be updated
	@PatchMapping("/screens/rows/{id}")
	public ResponseEntity<Screen> updateScreenRows(@Valid @PathVariable("id") int screenId, @RequestBody int rows) {
		Screen screenUpdate = screenService.updateScreenRows(screenId, rows);
		return new ResponseEntity<>(screenUpdate, HttpStatus.OK);
	}

	// Update the number of columns based on screen id
	// Patch Mapping since the only columns is to be updated
	@PatchMapping("/screens/columns/{id}")
	public ResponseEntity<Screen> updateScreenColumns(@Valid @PathVariable("id") int screenId,
			@RequestBody int columns) {
		Screen screenUpdate = screenService.updateScreenColumns(screenId, columns);
		return new ResponseEntity<>(screenUpdate, HttpStatus.OK);
	}

}

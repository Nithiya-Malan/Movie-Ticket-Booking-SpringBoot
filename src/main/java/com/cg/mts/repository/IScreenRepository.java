package com.cg.mts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Screen;

@Repository
public interface IScreenRepository extends JpaRepository<Screen,Integer>{
	
	Screen findByScreenName(String screenName);
	List<Screen> findByTheatreId(int theatreId);
}

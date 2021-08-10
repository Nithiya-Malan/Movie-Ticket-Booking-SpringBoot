package com.cg.mts.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.cg.mts.entities.Show;

public interface IShowRepository extends JpaRepository<Show,Integer>{

	List<Show> findByTheatreId(int theatreId);
	
	@Query(value = "SELECT * FROM show WHERE DATE(show.start_time)=:date" , nativeQuery = true)
	List<Show> viewShowList(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
	
}

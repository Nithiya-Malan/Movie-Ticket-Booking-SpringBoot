package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mts.entities.Movie;

public interface IMovieRepository extends JpaRepository<Movie, Integer> {

}

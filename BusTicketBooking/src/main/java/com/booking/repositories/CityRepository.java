package com.booking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.models.CityModel;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityModel, Integer> {
    List<CityModel> findAll();
}

package com.skytech.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skytech.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}

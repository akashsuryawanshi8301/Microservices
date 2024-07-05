package com.skytech.hotel.service;

import java.util.List;

import com.skytech.hotel.entities.Hotel;

public interface HotelService {
	
	Hotel createHotel(Hotel hotel);
	
	Hotel getHotelById(String hotelId);
	
	List<Hotel> getAllHotels();

}

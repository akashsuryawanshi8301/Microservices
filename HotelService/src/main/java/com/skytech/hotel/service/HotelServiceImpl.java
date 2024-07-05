package com.skytech.hotel.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skytech.hotel.entities.Hotel;
import com.skytech.hotel.exceptions.ResourceNotFoundException;
import com.skytech.hotel.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel createHotel(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotelById(String hotelId) {
		
		return hotelRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("hotel with given hotelId not found!!" + hotelId ));
	}

	@Override
	public List<Hotel> getAllHotels() {
		
		return hotelRepository.findAll();
	}

}

package com.skytech.rating.service;

import java.util.List;

import com.skytech.rating.entities.Rating;

public interface RatingService {
	
	Rating createRating(Rating rating);
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);
	
	List<Rating> getAllRatings();

}

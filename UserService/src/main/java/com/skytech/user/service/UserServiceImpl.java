package com.skytech.user.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skytech.user.entities.Hotel;
import com.skytech.user.entities.Rating;
import com.skytech.user.entities.User;
import com.skytech.user.externalservices.HotelService;
import com.skytech.user.repository.UserRepository;

import exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private org.slf4j.Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);

	

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		 User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given userLd not found on server :" + userId));
		 
		
		 // get rating from userid
		 Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/"+user.getUserId(), Rating[].class);
		 //logger.info("{}",ratingsOfUser);
		 
		 // convert array to list
		 List<Rating> ratings= Arrays.asList(ratingsOfUser);
		 
		 // iterate each rating and set hotels
		List<Rating> ratingList = ratings.stream().map(rating -> {
			 
			 //ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity("http://HOTELSERVICE/hotel/e8fe0967-803a-4b94-a145-2a86ad15909c", Hotel.class);
			 
				//Hotel hotel = hotelResponse.getBody();
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
				
				rating.setHotel(hotel);
				
				return rating;
			 
			 
		 }).collect(Collectors.toList());
		 // get hotel using ratingId
		
		 user.setRatings(ratingList);
		 
		 return user;
	}

	@Override
	public void deleteUser(String userId) {
		
		userRepository.findById(userId).ifPresentOrElse(user -> userRepository.deleteById(userId), () ->{
			throw new ResourceNotFoundException("User with given userLd not found on server :" + userId);
		});
		
	}

}

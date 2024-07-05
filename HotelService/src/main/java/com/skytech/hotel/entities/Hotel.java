package com.skytech.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
	
	@Id
	
	private String id;
	
	private String hotelName;
	
	private String hotelLocation;
	
	private String about;

}

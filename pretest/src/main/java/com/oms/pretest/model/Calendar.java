package com.oms.pretest.model;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Calendar {
	
	private String locationID;
	private String day;
	public LocalTime cutOffTime;

}

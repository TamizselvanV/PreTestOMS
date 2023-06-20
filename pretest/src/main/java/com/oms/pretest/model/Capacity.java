package com.oms.pretest.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Capacity {
	
	private String storeNo;
	private String productId;	
	private Double noOfOrdersAccepted;
	public LocalDate date;
	

}

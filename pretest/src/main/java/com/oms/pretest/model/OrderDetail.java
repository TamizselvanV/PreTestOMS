package com.oms.pretest.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDetail {
	
	private String orderNo;
	private String productID;
	private double quantity;
	private LocalDate createDate;

}

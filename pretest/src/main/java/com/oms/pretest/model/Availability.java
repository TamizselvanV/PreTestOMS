package com.oms.pretest.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Availability {
	
	private String storeNo;
	private String productId;
	private double availQty;
	private LocalDate availDate;

}

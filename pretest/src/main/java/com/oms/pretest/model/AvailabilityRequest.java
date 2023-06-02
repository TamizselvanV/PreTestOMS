package com.oms.pretest.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AvailabilityRequest {

	private String storeNo;
	private String productId;
	private int reqQty;
	private LocalDate reqDate;
	private String status;
}

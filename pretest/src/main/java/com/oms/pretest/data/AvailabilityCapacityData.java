package com.oms.pretest.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.oms.pretest.model.Availability;
import com.oms.pretest.model.Capacity;

public class AvailabilityCapacityData {
	
	public static List<Availability> availablilityList(){
		List<Availability> lsAvailability = new ArrayList<>();
		lsAvailability.add(new Availability("Store001", "Prod1", 1.0, LocalDate.of(2021, 2, 19)));
		lsAvailability.add(new Availability("Store001", "Prod1", 3.0, LocalDate.of(2021, 2, 20)));
		lsAvailability.add(new Availability("Store001", "Prod1", 0.0, LocalDate.of(2021, 2, 21)));		
		return lsAvailability;
	}

	public static List<Capacity> capactiyList(){
		List<Capacity> lsCapacity = new ArrayList<>();
		lsCapacity.add(new Capacity("Store001", "Prod1", 0.0, LocalDate.of(2021, 2, 19)));
		lsCapacity.add(new Capacity("Store001", "Prod1", 2.0, LocalDate.of(2021, 2, 20)));
		lsCapacity.add(new Capacity("Store001", "Prod1", 2.0, LocalDate.of(2021, 2, 21)));		
		return lsCapacity;
	}
	
}

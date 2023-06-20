package com.oms.pretest.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.oms.pretest.data.StoreCalendarData;
import com.oms.pretest.model.Calendar;

@Service
public class StoreAvailabilityService {

	public static List<Calendar> lsStoreAvailablity = StoreCalendarData.storeCalendarList();

	public Object getStoreAvailablity(String strRequest) {

	
		JSONObject jsonRequest = new JSONObject(strRequest);	
		String strStoreID = jsonRequest.get("storeID").toString();
		String strRequestDate = jsonRequest.get("requestDate").toString();

		String pattern = "yyyy-MM-dd'T'HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime requestDateAndTime = LocalDateTime.parse(strRequestDate, formatter);
		DayOfWeek strReqDayOfWeek = requestDateAndTime.getDayOfWeek();

		Predicate<Calendar> predicateStoreID = Calendar -> Calendar.getLocationID().equalsIgnoreCase(strStoreID);
		Predicate<Calendar> predicateCutOffTime = Calendar -> Calendar.getCutOffTime()
				.isAfter(requestDateAndTime.toLocalTime());
		Predicate<Calendar> predicateDayOfWeek = Calendar -> Calendar.getDay()
				.equalsIgnoreCase(strReqDayOfWeek.toString());
		Predicate<Calendar> predicateAllWeek = Calendar -> Calendar.getDay().equalsIgnoreCase("ALL");

		Calendar storeCal = lsStoreAvailablity.stream()
				.filter(predicateStoreID.and(predicateCutOffTime).and(predicateDayOfWeek.or(predicateAllWeek)))
				.findFirst().orElse(new Calendar("Invalid Store", null, null));

		if (storeCal.getLocationID().equalsIgnoreCase("Invalid Store")) {
			jsonRequest.put("Status", "Not Available");
		} else {
			jsonRequest.put("Status", "Available");
		}

		return jsonRequest.toString();

	}

}

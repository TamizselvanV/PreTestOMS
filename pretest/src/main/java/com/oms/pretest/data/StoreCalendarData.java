package com.oms.pretest.data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.oms.pretest.model.Calendar;

public class StoreCalendarData {

	public static List<Calendar> storeCalendarList() {
		List<Calendar> lsStoreCalen = new ArrayList<>();
		lsStoreCalen.add(new Calendar("STORE001", "ALL", LocalTime.of(13, 30)));
		lsStoreCalen.add(new Calendar("STORE002", "SUNDAY", LocalTime.of(13, 30)));
		lsStoreCalen.add(new Calendar("STORE003", "TUESDAY", LocalTime.of(13, 30)));
		return lsStoreCalen;
	}

}

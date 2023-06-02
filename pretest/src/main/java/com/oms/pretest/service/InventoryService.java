package com.oms.pretest.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.oms.pretest.data.AvailabilityCapacityData;
import com.oms.pretest.model.Availability;
import com.oms.pretest.model.AvailabilityRequest;
import com.oms.pretest.model.Capacity;

@Service
public class InventoryService {

	public static List<Availability> listAvail = AvailabilityCapacityData.availablilityList();
	public static List<Capacity> listCapacity = AvailabilityCapacityData.capactiyList();

	public Object getAvailAndCapacity(AvailabilityRequest request) throws InterruptedException, ExecutionException {

		LocalDate reqDate = request.getReqDate();
		ExecutorService executService = Executors.newFixedThreadPool(2);
		Future<Availability> avail = executService.submit(callAvailService(reqDate));
		Future<Capacity> capacity = executService.submit(callCapacityService(reqDate));
		executService.shutdown();

		double dbAvailability = avail.get().getAvailQty();
		double dbcapacity = capacity.get().getNoOfOrdersAccepted();
		AvailabilityRequest availResponse = request;
		if (dbAvailability == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else if (dbcapacity == 0) {

			availResponse.setStatus("No Capacity");
			return availResponse;
		} else {

			availResponse.setStatus("Available");
			return availResponse;
		}

	}

	private Callable<Availability> callAvailService(LocalDate reqDate) {
		return new Callable<Availability>() {
			public Availability call() throws Exception {
				return fetchAvailByProductID(reqDate);
			}
		};
	}

	private Callable<Capacity> callCapacityService(LocalDate reqDate) {
		return new Callable<Capacity>() {
			public Capacity call() throws Exception {
				return fetchCapacityByProductID(reqDate);
			}
		};
	}

	public Availability fetchAvailByProductID(LocalDate reqDate) {

		Predicate<? super Availability> predicate = Availability -> Availability.getAvailDate().isEqual(reqDate);
		Availability other = new Availability("No Availability", null, 0, null);
		return listAvail.stream().filter(predicate).findFirst().orElse(other);
	}

	public Capacity fetchCapacityByProductID(LocalDate reqDate) {

		Predicate<? super Capacity> predicate = Capacity -> Capacity.getDate().isEqual(reqDate);
		Capacity other = new Capacity("No Capacity", null, 0.0, null);
		return listCapacity.stream().filter(predicate).findFirst().orElse(other);
	}
}

package com.oms.pretest.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.oms.pretest.model.Availability;
import com.oms.pretest.model.AvailabilityRequest;
import com.oms.pretest.model.OrderStatusDTO;
import com.oms.pretest.service.InventoryService;
import com.oms.pretest.service.OrderDetailService;

@RestController
public class OMSController {

	private InventoryService invService;

	@RequestMapping("availabilityList")
	public List<Availability> availList() {
		return invService.listAvail;
	}

	public OMSController(InventoryService invService) {
		super();
		this.invService = invService;
	}

	@RequestMapping(value = "/getProdAvailability", method = RequestMethod.POST)
	public Object getProdAvailability(@RequestBody AvailabilityRequest request)
			throws InterruptedException, ExecutionException {

		return invService.getAvailAndCapacity(request);
	}
	
	@RequestMapping(value = "/getOrderStats", method = RequestMethod.POST)
	public Object getOrderStatsDetails(@RequestBody OrderStatusDTO request)
			throws InterruptedException, ExecutionException {

		return OrderDetailService.getOrderStat(request);
		
	}
	
}

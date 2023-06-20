package com.oms.pretest.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oms.pretest.data.StoreCalendarData;
import com.oms.pretest.model.Availability;
import com.oms.pretest.model.AvailabilityRequest;
import com.oms.pretest.model.Calendar;
import com.oms.pretest.model.OrderDetail;
import com.oms.pretest.model.OrderStatusDTO;
import com.oms.pretest.service.InventoryService;
import com.oms.pretest.service.OrderDetailService;
import com.oms.pretest.service.StoreAvailabilityService;

@RestController
public class OMSController {

	private InventoryService invService;
	private StoreAvailabilityService storeAvailService;
	private OrderDetailService orderDetailService;

	public OMSController(InventoryService invService, StoreAvailabilityService storeAvailService,
			OrderDetailService orderDetailService) {
		super();
		this.invService = invService;
		this.storeAvailService = storeAvailService;
		this.orderDetailService = orderDetailService;
	}

	@RequestMapping("availabilityList")
	public List<Availability> availList() {
		return invService.listAvail;
	}

	@RequestMapping(value = "/getProdAvailability", method = RequestMethod.GET)
	public Object getProdAvailability(@RequestBody AvailabilityRequest request)
			throws InterruptedException, ExecutionException {

		return invService.getAvailAndCapacity(request);
	}

	@RequestMapping(value = "/StoreCalendarLists", method = RequestMethod.GET)
	public List<Calendar> getStoreCalList() {
		return StoreCalendarData.storeCalendarList();
	}

	@RequestMapping(value = "/StoreAvailablility", method = RequestMethod.GET)
	public Object getStoreAvailability(@RequestBody String strRequest) {

		return storeAvailService.getStoreAvailablity(strRequest);

	}

	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
	public List<OrderDetail> fetchOrderList() {
		return orderDetailService.listAvail;
	}

	@RequestMapping(value = "/getOrderStats", method = RequestMethod.GET)
	public String getOrderStatsDetails(@RequestBody String request) throws InterruptedException, ExecutionException {

		return OrderDetailService.getOrderStat(request);

	}

}

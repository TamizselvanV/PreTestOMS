package com.oms.pretest.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.oms.pretest.data.OrderDetailsData;
import com.oms.pretest.model.OrderDetail;
import com.oms.pretest.model.OrderStatusDTO;

public class OrderDetailService {

	public static List<OrderDetail> listAvail = OrderDetailsData.OrderDetailList();

	public static Object getOrderStat(OrderStatusDTO request) {
		String statName = request.getStatName();

		Map<String, Double> prodDetails = new TreeMap();

		for (Iterator iterator = listAvail.iterator(); iterator.hasNext();) {
			OrderDetail orderDetail = (OrderDetail) iterator.next();
			String strProdID = orderDetail.getProductID();
			double dbQuantity = orderDetail.getQuantity();
			if (prodDetails.containsKey(strProdID)) {
				double dbExisitingQty = prodDetails.get(strProdID) + dbQuantity;
				prodDetails.put(strProdID, dbExisitingQty);
			} else {
				prodDetails.put(strProdID, dbQuantity);
			}
		}

		System.out.println("***********" + prodDetails.size());
		return prodDetails;
	}
}

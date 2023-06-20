package com.oms.pretest.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.oms.pretest.data.OrderDetailsData;
import com.oms.pretest.model.OrderDetail;

@Service
public class OrderDetailService {

	private static String ProdID = null;
	public static List<OrderDetail> listAvail = OrderDetailsData.OrderDetailList();

	public static String getOrderStat(String request) {

		JSONObject jsonObject = new JSONObject(request);
		String statName = jsonObject.getString("statName");

		Map<String, Double> productSales = listAvail.stream()
				.collect(Collectors.groupingBy(OrderDetail -> OrderDetail.getProductID(),
						Collectors.summingDouble(OrderDetail -> OrderDetail.getQuantity())));

		if ("MAX_SALE".equalsIgnoreCase(statName)) {

			ProdID = productSales.entrySet().stream().max(Comparator.comparingDouble(Map.Entry::getValue))
					.map(Map.Entry::getKey).orElse("No Item Found");
		} else if ("MIN_SALE".equalsIgnoreCase(statName)) {

			ProdID = productSales.entrySet().stream().min(Comparator.comparingDouble(Map.Entry::getValue))
					.map(Map.Entry::getKey).orElse("No Item Found");
		}

		List<OrderDetail> responseList = listAvail.stream()
				.filter(OrderDetail -> OrderDetail.getProductID().equalsIgnoreCase(ProdID))
				.collect(Collectors.toList());
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("ProdID", ProdID);
		jsonResponse.put("OrderList", responseList);

		return jsonResponse.toString();
	}
}

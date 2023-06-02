package com.oms.pretest.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.oms.pretest.model.OrderDetail;

public class OrderDetailsData {
	
	public static List<OrderDetail> OrderDetailList(){
		List<OrderDetail> lsOrderDetail = new ArrayList<>();
		lsOrderDetail.add(new OrderDetail("Order1", "Product1", 10.0, LocalDate.of(2021, 3, 16)));
		lsOrderDetail.add(new OrderDetail("Order2", "Product2", 5.0, LocalDate.of(2021, 3, 19)));
		lsOrderDetail.add(new OrderDetail("Order3", "Product1", 30.0, LocalDate.of(2021, 3, 16)));		
		lsOrderDetail.add(new OrderDetail("Order4", "Product4", 20.0, LocalDate.of(2021, 3, 20)));
		lsOrderDetail.add(new OrderDetail("Order5", "Product2", 20.0, LocalDate.of(2021, 3, 16)));
		return lsOrderDetail;
	}

}

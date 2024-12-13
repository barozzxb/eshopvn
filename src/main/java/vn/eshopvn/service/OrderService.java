package vn.eshopvn.service;

import java.util.List;

import vn.eshopvn.entity.Order;
import vn.eshopvn.entity.OrderDetail;
import vn.eshopvn.model.OrderSummary;

public interface OrderService {

	boolean addOrder(Order order);
	
	void setStatus(Order order, String status);
	
	void addOrderDetail(OrderDetail oDetail);

	void updateOrder(Order order);
	
	Order getOrder(String oid);
	
	public List<Order> getAllOrdersByUserId(String userid);
	
	public List<Order> getAllOrders();
	
	List<OrderSummary> getYearlyOrderSummary();

	List<OrderSummary> getMonthlyOrderSummary();

	int getTotalOrder();

	double getTotalRevenue();
	
	List<OrderDetail> getOrderItems(String oid);
}

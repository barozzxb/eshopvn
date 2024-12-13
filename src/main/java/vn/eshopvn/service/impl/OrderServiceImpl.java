package vn.eshopvn.service.impl;

import java.util.List;

import vn.eshopvn.dao.OrderDAO;
import vn.eshopvn.dao.impl.OrderDAOImpl;
import vn.eshopvn.entity.Order;
import vn.eshopvn.entity.OrderDetail;
import vn.eshopvn.model.OrderSummary;
import vn.eshopvn.service.OrderService;

public class OrderServiceImpl implements OrderService{

	OrderDAO oDAO = new OrderDAOImpl();
	
	@Override
	public boolean addOrder(Order order) {
		try {
			oDAO.addOrder(order);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void setStatus(Order order, String status) {
		oDAO.setStatus(order, status);
	}

	@Override
	public void addOrderDetail(OrderDetail oDetail) {
		oDAO.addOrderDetail(oDetail);
	}

	@Override
	public void updateOrder(Order order) {
		oDAO.updateOrder(order);
	}

	@Override
	public Order getOrder(String oid) {
		return oDAO.getOrder(oid);
	}

	@Override
	public List<Order> getAllOrdersByUserId(String userid) {
		return oDAO.getAllOrdersByUserId(userid);
	}

	@Override
	public List<Order> getAllOrders() {
		return oDAO.getAllOrders();
	}

	@Override
	public List<OrderSummary> getYearlyOrderSummary() {
		return oDAO.getYearlyOrderSummary();
	}

	@Override
	public List<OrderSummary> getMonthlyOrderSummary() {
		return oDAO.getMonthlyOrderSummary();
	}

	@Override
	public int getTotalOrder() {
		return oDAO.getTotalOrder();
	}

	@Override
	public double getTotalRevenue() {
		return oDAO.getTotalRevenue();
	}

	@Override
	public List<OrderDetail> getOrderItems(String oid) {
		return oDAO.getOrderItems(oid);
	}
}

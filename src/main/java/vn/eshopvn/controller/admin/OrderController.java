package vn.eshopvn.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.eshopvn.entity.Order;
import vn.eshopvn.service.OrderService;
import vn.eshopvn.service.impl.OrderServiceImpl;
import vn.eshopvn.ultis.OrderStatus;

@WebServlet(urlPatterns = {"/admin/orders", "/admin/order/detail"})
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	OrderService oServ = new OrderServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		
		if (url.contains("orders")) {
			
			List<Order> orders = oServ.getAllOrders();
			OrderStatus odStatus = new OrderStatus();
			req.setAttribute("status", odStatus.getOrderStatus());
			req.setAttribute("orders", orders);
			req.getRequestDispatcher("/views/admin/orders.jsp").forward(req, resp);
		} else if (url.contains("detail")) {
			String oid = req.getParameter("oid");
			Order order = oServ.getOrder(oid);
			req.setAttribute("order", order);
			req.getRequestDispatcher("/views/admin/order-detail.jsp").forward(req, resp);
		}
	}
}

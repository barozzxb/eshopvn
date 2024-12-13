package vn.eshopvn.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.eshopvn.entity.Account;
import vn.eshopvn.entity.Cart;
import vn.eshopvn.entity.CartItem;
import vn.eshopvn.entity.CartItemId;
import vn.eshopvn.service.CartService;
import vn.eshopvn.service.ProductService;
import vn.eshopvn.service.impl.CartServiceImpl;
import vn.eshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = { "/user/cart/view", "/user/cart/add", "/user/cart/remove", "/user/cart/make-order", "/user/cart/update" })
public class CartController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	CartService cServ = new CartServiceImpl();
	ProductService pServ = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Account acc = (Account) session.getAttribute("account");
		
		if (req.getAttribute("message") != null) {
			String message = req.getAttribute("message").toString();
			req.setAttribute("message", message);
		} else {
			String message = "";
		}
		
		if (acc == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		String url = req.getRequestURI();
		if (url.contains("/view")) {
			String userId = acc.getUserid();

			Cart cart = cServ.getCartByUserId(userId);

			if (cart == null) {
				throw new IOException("An error occured!");
			} else {
				List<CartItem> cartItems = cServ.getCartItemsByCartId(cart.getCartId());
				double totalPrice = 0;

				if (cartItems != null && !cartItems.isEmpty()) {
					totalPrice = cartItems.stream()
							.mapToDouble(item -> item.getQuantity() * item.getProduct().getPprice()).sum();
				}

				req.setAttribute("cart", cart);
				req.setAttribute("cartItems", cartItems);
				req.setAttribute("totalPrice", totalPrice);

				req.getRequestDispatcher("/views/user/cart.jsp").forward(req, resp);
			}
		} else if (url.contains("make-order")) {
			String cid = req.getParameter("cid");
			resp.sendRedirect(req.getContextPath() + "/user/order/make?cid=" + cid);
		}
		
		else if (url.contains("remove")) {
			String pid = req.getParameter("pid");
			if (cServ.removeCartItem(pid, cServ.getCartByUserId(acc.getUserid()).getCartId())) {
				resp.sendRedirect(req.getContextPath() + "/user/cart/view");
			}
			
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Account acc = (Account) session.getAttribute("account");

		if (acc == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		String url = req.getRequestURI();
		if (url.contains("add")) {
			String message = "";
			String productId = req.getParameter("pid");
			String userId = acc.getUserid();
			Cart cart = cServ.getCartByUserId(userId);
			if (cart != null) {
				if (cServ.isProductInCart(cart.getCartId(), productId)) {
					CartItem citem = cServ.getItemInCart(cart.getCartId(), productId);
					citem.setQuantity(citem.getQuantity()+1);
					cServ.updateCartItem(citem);
					message = "Cart updated!";
					req.setAttribute("message", message);
					resp.sendRedirect(req.getContextPath() + "/user/product/detail?pid=" + productId);
				} else {
					CartItem citem = new CartItem();
					CartItemId ciid = new CartItemId(cart.getCartId(), productId);
					citem.setCart(cart);
					citem.setCartItemId(ciid);
					citem.setProduct(pServ.findById(productId));
					citem.setQuantity(1);
					
					if (cServ.addCartItem(citem)) {
						req.setAttribute("message", "Updated to Cart!");
						resp.sendRedirect(req.getContextPath() + "/user/product/detail?pid=" + productId);
					}
				}
			}
		}
	}
}
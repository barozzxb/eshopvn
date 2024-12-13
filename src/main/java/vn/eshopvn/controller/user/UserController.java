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
import vn.eshopvn.entity.Genre;
import vn.eshopvn.entity.Product;
import vn.eshopvn.model.TopSellingProduct;
import vn.eshopvn.service.GenreService;
import vn.eshopvn.service.ProductService;
import vn.eshopvn.service.impl.GenreServiceImpl;
import vn.eshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/user/home"})
public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	GenreService genreServ = new GenreServiceImpl();
	ProductService prodServ = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			Account u = (Account) session.getAttribute("account");
			req.setAttribute("account", u);
			
			if (u.isDeactivated()) {
				req.getRequestDispatcher("/views/user/suspended.jsp").forward(req, resp);
			} else {
				List<Genre> genres = genreServ.findAll();
				req.setAttribute("genres", genres);
				
				List<Product> newprods = prodServ.top3new();
				req.setAttribute("topprod", newprods);
				
				List<TopSellingProduct> topSelling = prodServ.topSellingProducts();
				req.setAttribute("topSelling", topSelling);
				
				req.setAttribute("username", u.getUserid());
				req.setAttribute("password", u.getPassword());
				req.setAttribute("email", u.getEmail());
				req.setAttribute("name", u.getName());
				req.setAttribute("phone", u.getPhonenum());
				req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
			}
		}
	}
}

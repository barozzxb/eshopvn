package vn.eshopvn.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.eshopvn.entity.Genre;
import vn.eshopvn.entity.Product;
import vn.eshopvn.model.TopSellingProduct;
import vn.eshopvn.service.GenreService;
import vn.eshopvn.service.ProductService;
import vn.eshopvn.service.impl.GenreServiceImpl;
import vn.eshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/home"})
public class WebController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	GenreService genreServ = new GenreServiceImpl();
	ProductService prodServ = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Genre> genres = genreServ.findAll();
        req.setAttribute("genres", genres);
		
		List<Product> newprods = prodServ.top3new();
		req.setAttribute("topprod", newprods);
		
		List<TopSellingProduct> topSelling = prodServ.topSellingProducts();
		req.setAttribute("topSelling", topSelling);
		
		req.getRequestDispatcher("/views/guest/home.jsp").forward(req, resp);
	}
}

package vn.eshopvn.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.eshopvn.entity.Genre;
import vn.eshopvn.entity.Product;
import vn.eshopvn.service.GenreService;
import vn.eshopvn.service.ProductService;
import vn.eshopvn.service.impl.GenreServiceImpl;
import vn.eshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = { "/user/genres", "/user/genre/products" })
public class GenreController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    GenreService genreServ = new GenreServiceImpl();
    ProductService productServ = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        System.out.println("Request URL: " + url); // Log URL nhận được

        
        if (url.contains("/user/genre/products")) {
            String gid = req.getParameter("gid");  // Lấy gid từ URL

            if (gid != null && !gid.isEmpty()) {
                try {
                    Genre genre = genreServ.findById(gid); // Tìm genre theo gid

                    if (genre != null) {
                        List<Product> products = productServ.findByGenre(gid); // Lấy sản phẩm theo genre

                        req.setAttribute("genre", genre);
                        req.setAttribute("products", products);
                        req.getRequestDispatcher("/views/common-views/product/product-by-genre.jsp").forward(req, resp);
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/user/genres");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendRedirect(req.getContextPath() + "/user/genres");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/user/genres");
            }
        }
        else if (url.contains("/user/genres")) {
            System.out.println("Fetching all genres...");
            List<Genre> listgenre = genreServ.findAll(); // Get all genres

            req.setAttribute("listgenre", listgenre);

            List<Product> products = productServ.findAll(); // Get all products
            req.setAttribute("products", products);

            req.getRequestDispatcher("/views/user/product/genres.jsp").forward(req, resp);
        }
    
    }
}
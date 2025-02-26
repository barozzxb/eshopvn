package vn.eshopvn.controller.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.eshopvn.entity.Genre;
import vn.eshopvn.entity.Post;
import vn.eshopvn.entity.Product;
import vn.eshopvn.entity.Review;
import vn.eshopvn.service.GenreService;
import vn.eshopvn.service.PostService;
import vn.eshopvn.service.ProductService;
import vn.eshopvn.service.ReviewService;
import vn.eshopvn.service.impl.GenreServiceImpl;
import vn.eshopvn.service.impl.PostServiceImpl;
import vn.eshopvn.service.impl.ProductServiceImpl;
import vn.eshopvn.service.impl.ReviewServiceImpl;
import vn.eshopvn.ultis.Constant;

@WebServlet(urlPatterns = { "/user/products", "/user/product/detail", "/user/product/search", "/user/shop" })
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ProductService proSer = new ProductServiceImpl();
	GenreService genreServ = new GenreServiceImpl();
	PostService postSer=new PostServiceImpl();
	ReviewService reviewSer = new ReviewServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		HttpSession session = req.getSession();
		
		if (url.contains("products")) {
			String upPath = Constant.upDir;
			req.setAttribute("upPath", upPath);
			List<Product> products = proSer.findAll();
			req.setAttribute("products", products);
			List<Genre> genres = genreServ.findAll();
			req.setAttribute("genres", genres);
			List<Post> posts = postSer.findAll();
	        	posts.forEach(post -> {
	            	if (post.getContent() != null && post.getContent().length() > 100) {
	                    post.setContent(post.getContent().substring(0, 100) + "...");
	                }
	            });
	            req.setAttribute("posts", posts);

			req.getRequestDispatcher("/views/common-views/product/product-list.jsp").forward(req, resp);
		}

		else if (url.contains("detail")) {
			String pid = req.getParameter("pid");
			if (req.getAttribute("message") != null) {
				String message = req.getAttribute("message").toString();
				req.setAttribute("message", message);
			}
			
			if (pid == null || pid.isEmpty()) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is missing");
				return;
			}

			String referer = req.getHeader("Referer");
            String userRole = (String) req.getSession().getAttribute("role");
            if (referer == null || referer.isEmpty()) {
                if ("admin".equals(userRole)) {
                    referer = req.getContextPath() + "/admin/home";
                } else {
                    referer = req.getContextPath() + "/home/product";
                }
            }
            req.setAttribute("backUrl", referer);

			Product p = proSer.findById(pid);
			if (p == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
				return;
			}
			req.setAttribute("product", p);

			List<Review> reviews = reviewSer.findByProductId(pid);
            req.setAttribute("reviews", reviews);
			
            for (Review review : reviews) {
                if (review.getMediaUrl() != null) {
                    String mediaUrl = review.getMediaUrl();
                    if (mediaUrl.endsWith(".mp4") || mediaUrl.endsWith(".webm") || mediaUrl.endsWith(".ogg")) {
                        review.setVideo(true);  // Thêm thuộc tính cho review
                    } else {
                        review.setVideo(false);
                    }
                }
            }
            
         // Lưu sản phẩm đã xem vào session và chỉ giữ 3 sản phẩm gần nhất
            List<Product> viewedProducts = (List<Product>) session.getAttribute("viewedProducts");
            if (viewedProducts == null) {
                viewedProducts = new ArrayList<>();
            } else {
                viewedProducts = viewedProducts.stream()
                                               .filter(Objects::nonNull)
                                               .collect(Collectors.toList());
            }

            if (!viewedProducts.stream().anyMatch(v -> v.getPid().equals(pid))) {
                viewedProducts.add(0, p);
            }

            if (viewedProducts.size() > 3) {
                viewedProducts = viewedProducts.subList(0, 3);
            }

            session.setAttribute("viewedProducts", viewedProducts);

            req.getRequestDispatcher("/views/common-views/product/product-info.jsp").forward(req, resp);
        }

		else if (url.contains("search")) {
//			String keyword = req.getParameter("search");
//			List<Product> products = proSer.searchProducts(keyword);
//			req.setAttribute("products", products);
//			req.getRequestDispatcher("/views/common-views/product/product-list.jsp").forward(req, resp);
			// Lấy các tham số từ form tìm kiếm
	        String query = req.getParameter("query");
	        Integer rating = req.getParameter("rating") != null ? Integer.parseInt(req.getParameter("rating")) : null;
	        Timestamp createdAtFrom = req.getParameter("createdAtFrom") != null 
	        	    ? Timestamp.valueOf(req.getParameter("createdAtFrom")) 
	        	    : null;

	        	Timestamp createdAtTo = req.getParameter("createdAtTo") != null 
	        	    ? Timestamp.valueOf(req.getParameter("createdAtTo")) 
	        	    : null;

	        Integer minPrice = req.getParameter("minPrice") != null ? Integer.parseInt(req.getParameter("minPrice")) : null;
	        Integer maxPrice = req.getParameter("maxPrice") != null ? Integer.parseInt(req.getParameter("maxPrice")) : null;
	        Integer minQuantity = req.getParameter("minQuantity") != null ? Integer.parseInt(req.getParameter("minQuantity")) : null;
	        Integer maxQuantity = req.getParameter("maxQuantity") != null ? Integer.parseInt(req.getParameter("maxQuantity")) : null;
	        String sortBy = req.getParameter("sortBy");
	        boolean ascending = Boolean.parseBoolean(req.getParameter("ascending"));

	        // Tìm kiếm sản phẩm
	        List<Product> products = proSer.searchProducts(query, rating, createdAtFrom, createdAtTo, minPrice, maxPrice, minQuantity, maxQuantity, sortBy, ascending);

	        // Gửi kết quả về phía người dùng
	        req.setAttribute("products", products);
	        req.getRequestDispatcher("/views/common-views/product/product-list.jsp").forward(req, resp);

		} else if (url.contains("filter")) {
			String genresFilter = req.getParameter("genres") != null? req.getParameterValues("genres").toString() : "";
			int price = Integer.parseInt(req.getParameter("price"));
			List<Product> products = proSer.searchAndFilter(genresFilter, price);
			req.setAttribute("products", products);
			req.getRequestDispatcher("/views/common-views/product/product-info.jsp").forward(req, resp);
		} else if (url.contains("reset")) {
			List<Product> products = proSer.findAll();
			req.setAttribute("products", products);
			req.getRequestDispatcher("/views/common-views/product/product-info.jsp").forward(req, resp);
		}
	}
}
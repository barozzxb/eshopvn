package vn.eshopvn.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.eshopvn.entity.Post;
import vn.eshopvn.service.PostService;
import vn.eshopvn.service.impl.PostServiceImpl;

@WebServlet(urlPatterns = {"/admin/posts"})
public class PostController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	PostService poServ = new PostServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Post> censored = poServ.findAllCensoredPost();
		List<Post> uncensored = poServ.findAllUncensoredPost();
		
		req.setAttribute("censoredPost", censored);
		req.setAttribute("uncensoredPost", uncensored);
		
		req.getRequestDispatcher("/views/admin/posts.jsp").forward(req, resp);
	}
}

package vn.eshopvn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.eshopvn.ultis.Constant;

@WebServlet(urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("account");
		Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) { 
	                cookie.setValue(""); 
	                cookie.setMaxAge(0); 
	                cookie.setPath("/"); 
	                resp.addCookie(cookie); 
	                break;
	            }
	        }
	    }
		resp.sendRedirect("home");
	}
}
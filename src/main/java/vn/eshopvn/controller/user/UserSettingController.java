package vn.eshopvn.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.eshopvn.entity.Account;
import vn.eshopvn.service.AccountService;
import vn.eshopvn.service.impl.AccountServiceImpl;
import vn.eshopvn.ultis.PasswordUltis;

@WebServlet(urlPatterns = { "/user/settings", "/user/settings/profile", "/user/settings/change-password",
		"/user/settings/delete", })
public class UserSettingController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	AccountService service = new AccountServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			req.getRequestDispatcher("/views/user/settings.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		HttpSession session = req.getSession();

		String url = req.getRequestURI();
		if (url.contains("settings/profile")) {
			// String userid = req.getParameter("userid");
			String name = req.getParameter("name");
			String dob = req.getParameter("dob");
			String email = req.getParameter("email");
			String phonenum = req.getParameter("phonenum");
			String gender = req.getParameter("gender");
			String address = req.getParameter("address");

			Account acc = (Account) session.getAttribute("account");

			acc.setAddress(address);
			acc.setDob(dob);
			acc.setEmail(email);
			acc.setGender(gender);
			acc.setName(name);
			acc.setPhonenum(phonenum);

			String alertMsg = "";
			boolean updated = service.update(acc);
			if (updated == true) {
				alertMsg = "Successfully updated Account info";
				req.setAttribute("alert", alertMsg);
				resp.sendRedirect(req.getContextPath() + "/user/settings");
			} else {
				alertMsg = "Error occurred!";
				req.setAttribute("alert", alertMsg);
				resp.sendRedirect(req.getContextPath() + "/user/settings");
			}
		} else if (url.contains("settings/change-password")) {
			String oldpassword = req.getParameter("oldpassword");
			String newpassword = req.getParameter("newpassword");
			String cnewpassword = req.getParameter("c-newpassword");

			Account acc = (Account) session.getAttribute("account");
			if (PasswordUltis.checkPassword(oldpassword, acc.getPassword())) {
				if (cnewpassword.equals(newpassword)) {
					String hashedPassword = PasswordUltis.hashPassword(newpassword);
					acc.setPassword(hashedPassword);
					if (service.update(acc)) {
						resp.sendRedirect(req.getContextPath() + "/logout");
					} else {
						String alert = "Error";
						req.setAttribute("alert", alert);
						resp.sendRedirect(req.getContextPath() + "/user/settings/change-password");
					}
				} else {
					String alert = "Confirm Password does not match!";
					req.setAttribute("alert", alert);
					resp.sendRedirect(req.getContextPath() + "/user/settings/change-password");
				}
			} else {
				String alert = "Wrong old password!";
				req.setAttribute("alert", alert);
				resp.sendRedirect(req.getContextPath() + "/user/settings/change-password");
			}

		}
	}
}
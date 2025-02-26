package vn.eshopvn.controller.otp;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.eshopvn.service.AccountService;
import vn.eshopvn.service.impl.AccountServiceImpl;
import vn.eshopvn.ultis.EmailUltis;

@WebServlet(urlPatterns = { "/send-otp" })
public class OTPController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    AccountService aSer = new AccountServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get email from request
    	HttpSession session = req.getSession();
    	String email = (String) session.getAttribute("email");
        String otp = generateOTP();  // Generate OTP
        boolean sent = sendOTPEmail(email, otp);  // Send OTP to email
        //boolean sent = true;
        // Save OTP in session for later verification
        if (sent) {
            req.getSession().setAttribute("otp", otp);
        }

        // Send response back to client (JSON response)
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print("{\"success\": " + sent + "}");
        out.flush();
    }

    // Generate OTP
    private String generateOTP() {
        // Simple 6-digit random OTP generation
        int otp = (int)(Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }

    // Send OTP email
    private boolean sendOTPEmail(String email, String otp) {
        try {
            EmailUltis.sendOTPEmail(email, otp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


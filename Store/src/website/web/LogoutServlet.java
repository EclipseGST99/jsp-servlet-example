package website.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Clear session credentials
        HttpSession session = request.getSession();
        session.setAttribute("username", null);
        session.setAttribute("password", null);
        session.setAttribute("currentProductId", null);
        session.setAttribute("cart", null);

        // Redirect to the login page
        RequestDispatcher dispatcher = request.getRequestDispatcher("login");
        dispatcher.forward(request, response);
    }
}

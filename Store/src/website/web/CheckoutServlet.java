package website.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.MessageFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckoutServlet extends HttpServlet {
    private RequestDispatcher checkoutJsp;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        checkoutJsp = context.getRequestDispatcher("/WEB-INF/jsp/checkout.jsp");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // If not logged in then redirect to the login page
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("password") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
            dispatcher.forward(request, response);
            return;
        }

        // Display total purchase amount
        request.setAttribute("returnToLoginDisplay", "none");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        request.setAttribute("message",
                "<font color=\"red\" style=\"font-weight:bold;\">Total Purchase: $" + decimalFormat.format(session.getAttribute("totalPurchase")) + "</font>");
        checkoutJsp.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Store", "root", "AasasQqwqw");
            Statement statement = connection.createStatement();

            // Determine the customer ID
            session.getAttribute("username");
            ResultSet resultSet = statement.executeQuery("select CustomerId from Customers where UserName='csmith'");
            resultSet.next();
            int customerId = resultSet.getInt(1);

            // Insert a new order into the database
            String insertScript = MessageFormat.format(
                    "insert into Orders values(null, ''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'', ''{6}'');",
                    session.getAttribute("cart"),
                    customerId,
                    1,
                    session.getAttribute("totalPurchase"),
                    request.getParameter("creditCardNumber"),
                    request.getParameter("expirationDate"),
                    request.getParameter("securityCode"));
            statement.executeUpdate(insertScript);
            connection.close();
        } catch (SQLException e) {
            request.setAttribute("message", "<font color=\"red\">An unexpected error occurred<font>");
            checkoutJsp.forward(request, response);
            return;
        }

        // Update the page
        request.setAttribute("formDisplay", "none");
        request.setAttribute("returnToLoginDisplay", "visible");
        request.setAttribute("message", "<font color=\"green\">Purchase processed successfully</font>");
        session.setAttribute("totalPurchase", 0.0);
        session.setAttribute("cart", "");
        checkoutJsp.forward(request, response);
    }
}

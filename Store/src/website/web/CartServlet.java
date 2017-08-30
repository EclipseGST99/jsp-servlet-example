package website.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {
    private RequestDispatcher cartJsp;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        cartJsp = context.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession();

        // If not logged in then redirect to the login page
        if (session.getAttribute("username") == null || session.getAttribute("password") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
            dispatcher.forward(request, response);
            return;
        }

        // Get the cart
        String cart = (String) session.getAttribute("cart");
        Set<String> cartSet = deserializeCart(cart);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String cartPageBody = "<table>";
        Double totalPurchase = 0.0;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Store", "root", "AasasQqwqw");
            for (String itemId : cartSet) {
                if (itemId.equals("")) continue;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "select * from products where ProductId=" + itemId + ";");
                resultSet.next();
                String description = resultSet.getString("Description");
                Double price = Double.parseDouble(resultSet.getString("Price"));
                totalPurchase += price;
                cartPageBody += "<tr>\n" +
                        "<td align=\"left\" style=\"font-family:'Lucida Sans Unicode', 'Lucida Grande', sans-serif;\">" +
                        description + "</td>\n" +
                        "<td align=\"right\" style=\"font-family:'Lucida Sans Unicode', 'Lucida Grande', sans-serif;\">$" +
                        decimalFormat.format(price) + "</td>\n" +
                        "</tr>";
            }

            if (totalPurchase == 0) {
                cartPageBody = "No items in cart<br><br>";
                request.setAttribute("checkoutButtonDisplay", "none");
            } else {
                cartPageBody += "<tr>" +
                        "<td align=\"left\" style=\"width:300px; font-family:'Lucida Sans Unicode', 'Lucida Grande', sans-serif; color:red; font-weight:bold;\">" +
                        "TOTAL:</td>\n" +
                        "<td align=\"right\" style=\"width:100px; font-family:'Lucida Sans Unicode', 'Lucida Grande', sans-serif; color:red;\">$" +
                        decimalFormat.format(totalPurchase) + "</td>\n" +
                        "</tr>\n" +
                        "</table>";
                session.setAttribute("totalPurchase", totalPurchase);
            }
            connection.close();
        } catch (SQLException e) {
            cartPageBody += "<font color=\"red\">An unexpected error occurred</font>";
        }

        request.setAttribute("items", cartPageBody);
        cartJsp.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cartJsp.forward(request, response);
    }

    private Set<String> deserializeCart(String serializedCart) {
        String[] cartStringArray = serializedCart.split(",");
        return new HashSet<String>(Arrays.asList(cartStringArray));
    }
}

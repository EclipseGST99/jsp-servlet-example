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

public class ProductServlet extends HttpServlet {
    private RequestDispatcher productJsp;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        productJsp = context.getRequestDispatcher("/WEB-INF/jsp/product.jsp");

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

        request.setAttribute("addedToCartMessage", "<br>");

        updatePage(request);
        productJsp.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession();

        // If not logged in then redirect to the login page
        if (session.getAttribute("username") == null || session.getAttribute("password") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
            dispatcher.forward(request, response);
            return;
        }

        request.setAttribute("addedToCartMessage", "<br>");

        // If current product id is null then set it to 1
        if (session.getAttribute("currentProductId") == null) session.setAttribute("currentProductId", 1);

        // If cart is null then set it to 1
        if (session.getAttribute("cart") == null) session.setAttribute("cart", "");

        // Get the current product id
        int currentProductId = (int) session.getAttribute("currentProductId");

        // If the add to cart button was clicked then add the currently viewed item
        if (request.getParameter("addToCart") != null) {
            String serializedCart = (String) session.getAttribute("cart");
            Set<String> cart = deserializeCart(serializedCart);
            cart.add(Integer.toString(currentProductId));
            serializedCart = serializeCart(cart);
            session.setAttribute("cart", serializedCart);

            request.setAttribute("addedToCartMessage", "Item was added to your shopping cart");
        }

        // Determine the number of products currently in the database
        int productCount = determineNumberOfProductsInDb();

        // If the currently viewed product is no longer in the database then select the last product
        if (currentProductId > productCount) session.setAttribute("currentProductId", productCount);

        // If the previous or the next button was clicked then update the current product id
        if (request.getParameter("previous") != null) {
            if (currentProductId > 1) session.setAttribute("currentProductId", currentProductId - 1);
        } else if (request.getParameter("next") != null) {
            if (currentProductId < productCount) session.setAttribute("currentProductId", currentProductId + 1);
        }

        updatePage(request);
        productJsp.forward(request, response);
    }

    private void updatePage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Store", "root", "AasasQqwqw");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from products where ProductId=" + session.getAttribute("currentProductId") + ";");
            resultSet.next();
            request.setAttribute("description", resultSet.getString("Description"));
            request.setAttribute("price", decimalFormat.format(resultSet.getDouble("Price")));
            request.setAttribute("productImage", "image/" + resultSet.getString("Image"));

            // Determine if the currently viewed product is in stock
            if (resultSet.getInt("Count") > 0) {
                request.setAttribute("inStockColor", "green");
                request.setAttribute("inStock", "  In stock  ");
            } else {
                request.setAttribute("inStockColor", "red");
                request.setAttribute("inStock", "Out of stock");
            }

            connection.close();
        } catch (SQLException e) {
            // Do nothing
        }
    }

    private int determineNumberOfProductsInDb() {
        int productCount = 0;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Store", "root", "AasasQqwqw");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from products");
            resultSet.next();
            productCount = resultSet.getInt(1);
            connection.close();

        } catch (SQLException e) {
            // Do nothing
        }

        return productCount;
    }

    private String serializeCart(Set<String> cart) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String item : cart) {
            stringBuilder.append(item);
            stringBuilder.append(',');
        }

        return stringBuilder.toString();
    }

    private Set<String> deserializeCart(String serializedCart) {
        String[] cartStringArray = serializedCart.split(",");
        return new HashSet<String>(Arrays.asList(cartStringArray));
    }
}
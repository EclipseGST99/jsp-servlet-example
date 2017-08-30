package website.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    private RequestDispatcher loginJsp;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        loginJsp = context.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loginJsp.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (ValidateCredentials(username, password)) {
            // Store credentials in session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);

            // Set the currently viewed product to #1
            session.setAttribute("currentProductId", 1);

            // Redirect to the product page
            RequestDispatcher dispatcher = request.getRequestDispatcher("product");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("failedLoginMessage", "Invalid username or password");
            loginJsp.forward(request, response);
        }
    }

    private boolean ValidateCredentials(String username, String password) {
        boolean validated = false;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Store", "root", "AasasQqwqw");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    MessageFormat.format("select count(*) from customers where username=''{0}'' and password=''{1}'';", username, password));
            resultSet.next();
            validated = (resultSet.getInt(1) > 0);
            connection.close();

        } catch (SQLException e) {
            // Do nothing
        }

        return validated;
    }
}
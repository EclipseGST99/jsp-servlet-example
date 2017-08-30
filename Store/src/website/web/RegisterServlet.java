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


public class RegisterServlet extends HttpServlet {
    private RequestDispatcher registerJsp;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        registerJsp = context.getRequestDispatcher("/WEB-INF/jsp/register.jsp");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        registerJsp.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Store", "root", "AasasQqwqw");

            String desiredUsername = request.getParameter("desiredUsername");
            String password = request.getParameter("password");
            String verifyPassword = request.getParameter("verifyPassword");

            // Field validation
            if (desiredUsername == "") {
                request.setAttribute("registrationMessage", "Please enter a desired username");
                registerJsp.forward(request, response);
                return;
            }

            if (password == "") {
                request.setAttribute("registrationMessage", "Please enter a password");
                registerJsp.forward(request, response);
                return;
            }

            if (verifyPassword == "") {
                request.setAttribute("registrationMessage", "Please verify password");
                registerJsp.forward(request, response);
                return;
            }


            // Check if desired username is already in the database
            Statement statement = connection.createStatement();
            ResultSet matchingUsernameCount = statement.executeQuery(
                    MessageFormat.format("select count(*) from customers where username=''{0}'';", desiredUsername));
            matchingUsernameCount.next();
            int temp = matchingUsernameCount.getInt(1);
            if (temp > 0) {
                request.setAttribute("registrationMessage", desiredUsername + " is a username already in use");
                registerJsp.forward(request, response);
                return;
            }

            // Check if passwords don't match
            if (!password.equals(verifyPassword)) {
                request.setAttribute("registrationMessage", "Passwords don't match");
                registerJsp.forward(request, response);
                return;
            }

            // Insert new customer into the database
            String insertScript = MessageFormat.format(
                    "insert into Addresses values(null, ''{0}'', '''', ''{1}'', ''{2}'', ''{3}''); ",
                    request.getParameter("street1"),
                    request.getParameter("city"),
                    request.getParameter("state"),
                    request.getParameter("zipCode"));
            statement.executeUpdate(insertScript);

            insertScript = MessageFormat.format(
                    "insert into Customers values(null, ''{0}'', ''{1}'', ''{2}'', last_insert_id(), last_insert_id(), ''{3}'', ''{4}'', ''{5}'', ''{6}'');",
                    request.getParameter("firstName"),
                    request.getParameter("middleName"),
                    request.getParameter("lastName"),
                    request.getParameter("phoneNumber"),
                    request.getParameter("email"),
                    request.getParameter("desiredUsername"),
                    request.getParameter("password"));
            statement.executeUpdate(insertScript);

            connection.close();
        } catch (SQLException e) {
            request.setAttribute("registrationMessage", "An unexpected error occurred");
            registerJsp.forward(request, response);
            return;
        }

        request.setAttribute("formDisplay", "none");
        request.setAttribute("registrationMessage", "<br><div style=\"color:green;\">Registration Successful!<br><br>Have fun shopping :-)<div><br>");
        registerJsp.forward(request, response);
    }
}
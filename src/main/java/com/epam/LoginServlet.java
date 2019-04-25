package com.epam;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login.length() < 1 || password.length() < 1 || (login.equals("admin") && !password.equals("pass"))
                || login.equals("Guest")) {
            request.setAttribute("error", "Error message");
            request.getRequestDispatcher("/locale").include(request, response);
        } else {
            session.setAttribute(login, password);
            request.getRequestDispatcher("/redirect").forward(request, response);
        }
    }
}

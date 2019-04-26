package com.epam;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;

@WebServlet("/log_out")
public class LogOutServlet extends HttpServlet {

    public LogOutServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (session.getAttributeNames().hasMoreElements()) {
            String login = (String) session.getAttribute("login");
            session.removeAttribute(login);
        }
        request.getRequestDispatcher("/locale").include(request, response);
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}


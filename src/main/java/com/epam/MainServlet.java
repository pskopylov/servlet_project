package com.epam;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    public MainServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        request.getRequestDispatcher("/locale").include(request, response);
        if (!session.getAttributeNames().hasMoreElements()) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/logged.jsp").forward(request, response);
        }
    }
}

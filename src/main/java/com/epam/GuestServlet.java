package com.epam;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;

@WebServlet("/guest")
public class GuestServlet extends HttpServlet {

    public GuestServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        session.setAttribute("Guest", "guest");
        request.getRequestDispatcher("/locale").include(request, response);
        session.removeAttribute("Guest");
        request.getRequestDispatcher("/WEB-INF/logged.jsp").forward(request, response);
    }
}

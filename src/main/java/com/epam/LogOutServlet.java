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
        HttpSession session = request.getSession();

        if (session.getAttributeNames().hasMoreElements()) {
            String name = session.getAttributeNames().nextElement();
            session.removeAttribute(name);
        }
        request.getRequestDispatcher("/locale").forward(request, response);
    }
}


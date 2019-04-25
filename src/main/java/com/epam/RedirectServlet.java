package com.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet{

    public RedirectServlet(){
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
        Properties properties = ReadProperties.getProperties(findLocale(request));
        String login = session.getAttributeNames().nextElement();

        if (login.equals("Guest")) {
            login = properties.getProperty("text.guest");
            session.removeAttribute(login);
        }
        request.setAttribute("text", properties.getProperty("text.text").replaceAll("login", login));
        request.setAttribute("logOut", properties.getProperty("text.logOut"));
        request.getRequestDispatcher("WEB-INF/logged.jsp").forward(request, response);
    }

    private String findLocale(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("locale")) {
                    return cookie.getValue();
                }
            }
        }
        return request.getLocale().getLanguage();
    }
}

package com.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

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
        String login = session.getAttributeNames().nextElement();

        if (login.equals("Guest")) {
            login = translateGuest(request);
            session.removeAttribute(login);
        }
        request.setAttribute("text", createLoginText(request, login));
        request.getRequestDispatcher("WEB-INF/logged.jsp").include(request, response);
    }

    private String createLoginText(HttpServletRequest request, String login) {
        String locale = findLocale(request);

        switch (locale) {
            case "ru":
                return "Вы зашли как <i>" + login + "</i>";
            case "de":
                return "Sie sind als <i>" + login + " </i>angemeldet";
            default:
                return "You are logged as <i>" + login + "</i>";
        }
    }

    private String translateGuest(HttpServletRequest request){
        String locale = findLocale(request);
        switch (locale) {
            case "ru":
                return "Гость";
            case "de":
                return "Der Gast";
            default:
                return "Guest";
        }
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

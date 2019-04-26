package com.epam;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.util.*;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {
    private Properties supportedLanguages;
    private String requestLocale;
    private String key = "locale";

    public LocaleServlet() {
        supportedLanguages = new Properties();
        supportedLanguages.put("English", "en");
        supportedLanguages.put("Deutsch", "de");
        supportedLanguages.put("Russian", "ru");
        requestLocale = (String) supportedLanguages.get("English");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (!getCookie(request))
            detectLocale(request);
        translatePage(request);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String locale = request.getParameter("selectLanguage");
        requestLocale = locale != null ? locale : requestLocale;

        Cookie cookie = new Cookie(key, requestLocale);
        response.addCookie(cookie);

        translatePage(request);
        request.getRequestDispatcher("/WEB-INF/login.jsp").include(request, response);
    }

    private void detectLocale(HttpServletRequest request) {
        String locale = request.getLocale().getLanguage();
        if (supportedLanguages.contains(locale))
            requestLocale = locale;
    }

    private void translatePage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Properties properties = ReadProperties.getProperties(requestLocale);
        if (properties != null) {
            request.setAttribute("enterLogin", properties.getProperty("text.enterLogin"));
            request.setAttribute("enterPassword", properties.getProperty("text.enterPassword"));
            request.setAttribute("loginUser", properties.getProperty("text.loginUser"));
            request.setAttribute("enterGuest", properties.getProperty("text.enterGuest"));
            request.setAttribute("select" + Character.toUpperCase(requestLocale.charAt(0)) + requestLocale.charAt(1), "selected");
            request.setAttribute("en", properties.getProperty("text.en"));
            request.setAttribute("ru", properties.getProperty("text.ru"));
            request.setAttribute("de", properties.getProperty("text.de"));
            request.setAttribute("logOut", requestLocale);
            if (request.getAttribute("error") != null)
                request.setAttribute("error", properties.getProperty("text.error"));
            if (session.getAttributeNames().hasMoreElements()) {
                String login = (String) session.getAttribute("login");
                if (login.equals("guest"))
                    login = properties.getProperty("text.guest");
                request.setAttribute("text", properties.getProperty("text.loginText").replaceAll("login", login));
                request.setAttribute("logOut", properties.getProperty("text.logOut"));
            }
        }
    }

    private boolean getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    requestLocale = cookie.getValue();
                    return true;
                }
            }
        }
        return false;
    }
}

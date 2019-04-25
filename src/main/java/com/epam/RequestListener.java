package com.epam;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener
public class RequestListener implements ServletRequestListener {

    public RequestListener() {
    }

    public void requestDestroyed(ServletRequestEvent arg) {
        String requester = processRequest(arg);
        System.out.println("Request from " + requester + " was destroyed.");
    }

    public void requestInitialized(ServletRequestEvent arg) {
        String requester = processRequest(arg);
        System.out.println("Request from " + requester + " was created.");
    }

    private String processRequest(ServletRequestEvent arg){
        String requester;
        HttpServletRequest request = (HttpServletRequest) arg.getServletRequest();
        HttpSession session = request.getSession();
        if (!session.getAttributeNames().hasMoreElements()){
            requester = request.getContextPath();
        } else {
            requester = session.getAttributeNames().nextElement();
        }
        return requester;
    }
}

package com.epam;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;

@WebFilter(filterName = "CharsetFilter", urlPatterns = {"/login", "/guest", "/locale"},
        initParams = {@WebInitParam(name = "characterEncoding", value = "UTF-8")})
public class CharsetFilter implements Filter {

    private String encoding;

    public void init(FilterConfig fConfig) {
        encoding = fConfig.getInitParameter("characterEncoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}

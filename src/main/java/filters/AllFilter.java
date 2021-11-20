package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import state.AppState;
import state.UnLoginState;

public class AllFilter implements Filter {
    private String encoding;

    public AllFilter() {
    }

    public void init(FilterConfig fconfig) throws ServletException {
        encoding = fconfig.getInitParameter("Encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=" + encoding);
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpSession session = rq.getSession();
        AppState loginState = (AppState) session.getAttribute("loginState");
        if (loginState == null) {
            loginState = new UnLoginState();
            session.setAttribute("loginState", loginState);
            session.setAttribute("headerContent", "<a href='/iqds/login.jsp'>登录</a>");
            session.setAttribute("manager", 0);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}

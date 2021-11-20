package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import others.Page;
import state.AppState;

public class MeFilter implements Filter {

    public MeFilter() {
    }

    public void init(FilterConfig fconfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Object cq = session.getAttribute("correctq");
        Object tq = session.getAttribute("totalq");
        Object kls = session.getAttribute("knowledges");

        AppState loginState = (AppState) session.getAttribute("loginState");
        if (loginState.getType() == 0)
            Page.redirect(session, resp, "/iqds", "尚未登录！", 2, "主页");
        else if (tq == null || cq == null || kls == null)
            resp.sendRedirect("/iqds/me");
        else
            chain.doFilter(request, response);
    }

    public void destroy() {
    }
}

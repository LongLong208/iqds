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

public class DoQuestionFilter implements Filter {

    public DoQuestionFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        HttpServletResponse resp = (HttpServletResponse) response;

        String mode = (String) session.getAttribute("mode");
        String lastMode = (String) session.getAttribute("lastMode");
        if (mode != null)
            chain.doFilter(request, response);
        else {
            if (lastMode != null) {
                resp.sendRedirect("/iqds/doquestion?mode=" + lastMode);
            } else
                resp.sendRedirect("/iqds/");
        }
    }

    @Override
    public void destroy() {

    }

}

package others;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Page {
    private Page() {
    }

    /**
     * 
     * @param url  返回页面的url
     * @param str1 标题栏
     * @param time 返回页面前等待的时间
     * @param str2 返回页面的名字
     * 
     **/
    public static void redirect(HttpSession session, HttpServletResponse response, String url, String str1, int time,
            String str2) throws IOException {
        session.setAttribute("url", url);
        session.setAttribute("str1", str1);
        session.setAttribute("time", time);
        session.setAttribute("str2", str2);
        response.sendRedirect("/iqds/count.jsp");
    }
}

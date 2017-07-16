package jianshu.datalab.xin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mingfei.net@gmail.com
 *         2017/6/29 23:14
 *         https://github.com/thu/jianshu
 */
@WebServlet(urlPatterns = "/cookie")
public class Cookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getSession().getId());

        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("key","value");
        resp.addCookie(cookie);

        javax.servlet.http.Cookie[] cookies = req.getCookies();
        for (javax.servlet.http.Cookie aCookie : cookies) {
            System.out.println(aCookie.getName() + "\t" + aCookie.getValue());
            System.out.println(aCookie.getDomain() + "\t" + aCookie.getMaxAge());

        }
    }
}

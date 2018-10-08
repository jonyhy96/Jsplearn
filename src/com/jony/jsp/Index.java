package com.jony.jsp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.print("<html>");
        pw.print("<body>");
        pw.print("<form action=login method=post>");
        pw.print("用户名<input type=text name=username></br>");
        pw.print("密码<input type=password name=password></br>");
        pw.print("提交<input type=submit value=submit />");
        pw.print("</form>");
        pw.print("</body>");
        pw.print("</html>");
        Cookie cookie = new Cookie("username","admin");
        cookie.setMaxAge(10);
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}

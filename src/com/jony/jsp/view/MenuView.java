package com.jony.jsp.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MenuView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.print("<html>");
        pw.print("<body><center>");
        pw.print("<a href=admin>管理用户</a></br>");
        pw.print("<a href=insertCL>添加用户</a></br>");
        pw.print("<center></body>");
        pw.print("</html>");
    }
}

package com.jony.jsp.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CallbackView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.print("<html>");
        pw.print("<head><meta http-equiv=refresh content=3;URL=admin></head>");
        pw.print("<body><center>");
        pw.print("<h>"+req.getParameter("operation")+" "+req.getParameter("status")+"3秒后返回主页</h1>");
        pw.print("<center></body>");
        pw.print("</html>");
    }
}

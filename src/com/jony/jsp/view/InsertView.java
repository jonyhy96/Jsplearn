package com.jony.jsp.view;

import com.jony.jsp.model.UserBean;
import com.jony.jsp.model.UserBeanService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InsertView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        UserBeanService userBeanService = new UserBeanService();
        pw.print("<html>");
        pw.print("<head>"+
                "  <meta charset=\"UTF-8\">" +
                "</head>");
        pw.print("<body><center>");
        pw.print("<form action=insertCL method=post>");
        pw.print("用户名:<input type=text name=uName></br>");
        pw.print("城市:<input type=text name=uCity></br>");
        pw.print("年龄:<input type=text name=uAge></br>");
        pw.print("<input type=submit onClick=\"return window.confirm('确认添加？')\" value=添加>");
        pw.print("</form>");
        pw.print("<center></body>");
        pw.print("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf8");
        UserBean userBean = new UserBean(
                req.getParameter("uName"),
                req.getParameter("uCity"),
                Integer.parseInt(req.getParameter("uAge")));
        UserBeanService userBeanService = new UserBeanService();
        if(userBeanService.insertUser(userBean)){
            resp.sendRedirect("callback?operation=insert&status=success");
        }else{
            resp.sendRedirect("callback?operation=insert&status=failed");
        }
    }
}

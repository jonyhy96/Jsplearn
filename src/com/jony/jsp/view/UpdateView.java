package com.jony.jsp.view;

import com.jony.jsp.model.UserBean;
import com.jony.jsp.model.UserBeanService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        UserBeanService userBeanService = new UserBeanService();
        UserBean userBean = userBeanService.getUser(Integer.parseInt(req.getParameter("userId")));
        pw.print("<html>");
        pw.print("<body><center>");
        pw.print("<form action=updateCL method=post>");
        pw.print("ID<input readonly type=text name=uId value="+userBean.getId()+"></br>");
        pw.print("用户名<input readonly type=text name=uName value="+userBean.getName()+"></br>");
        pw.print("城市<input type=text name=uCity value="+userBean.getCity()+"></br>");
        pw.print("年龄<input type=text name=uAge value="+userBean.getAge()+"></br>");
        pw.print("<input type=submit onClick=return window.confirm('确认修改？') value=提交>");
        pw.print("</form>");
        pw.print("<center></body>");
        pw.print("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserBean userBean = new UserBean(Integer.parseInt(req.getParameter("uId")),
                req.getParameter("uName"),
                req.getParameter("uCity"),
                Integer.parseInt(req.getParameter("uAge")));
        UserBeanService userBeanService = new UserBeanService();
        if(userBeanService.updateUser(userBean)){
            resp.sendRedirect("callback?operation=update&status=success");
        }else{
            resp.sendRedirect("callback?operation=update&status=failed");
        }
    }
}

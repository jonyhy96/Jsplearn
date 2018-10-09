package com.jony.jsp.view;

import com.jony.jsp.model.UserBean;
import com.jony.jsp.model.UserBeanService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SearchView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        UserBeanService userBeanService = new UserBeanService();
        UserBean userBean = userBeanService.getUserByName(req.getParameter("uName"),
                Boolean.parseBoolean(req.getParameter("type")));
        if(userBean!=null) {
            pw.print("<html>");
            pw.print("<body><center>");
            pw.print("ID<input readonly type=text name=uId value=" + userBean.getId() + "></br>");
            pw.print("用户名<input readonly type=text name=uName value=" + userBean.getName() + "></br>");
            pw.print("城市<input readonly type=text name=uCity value=" + userBean.getCity() + "></br>");
            pw.print("年龄<input readonly  type=text name=uAge value=" + userBean.getAge() + "></br>");
            pw.print("<center></body>");
            pw.print("</html>");
        }else{
            pw.print("没有该用户");
        }
    }
}

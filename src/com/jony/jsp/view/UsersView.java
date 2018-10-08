package com.jony.jsp.view;

import com.jony.jsp.model.UserBean;
import com.jony.jsp.model.UserBeanService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UsersView extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserBeanService userBeanService = new UserBeanService();
        if(req.getSession().getAttribute("user")==null){
            resp.sendRedirect("index");
        }
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        int pageSize = 3;
        int pageNow;
        pageNow = req.getParameter("pageNow")!=null?Integer.valueOf(req.getParameter("pageNow")):1;
        int pageNomber = userBeanService.getPageNomber(pageSize);
        ArrayList<UserBean> userBeans = new UserBeanService().getUsers(pageSize,pageNow);
        pw.print("<html>");
        pw.print("<head>" +
                "<meta charset=UTF-8>" +
                "</head>");
        pw.print("<body><center>");
        pw.print("访问次数:"+this.getServletContext().getAttribute("count"));
        pw.print("<table border=1>");
        pw.print("<tr><th>ID</th><th>Name</th><th>City</th><th>Age</th></tr>");
        for (UserBean u:userBeans
             ) {
            pw.print("<tr>");
            pw.print("<td>"+u.getId()+"</td>");
            pw.print("<td>"+u.getName()+"</td>");
            pw.print("<td>"+u.getCity()+"</td>");
            pw.print("<td>"+u.getAge()+"</td>");
            pw.print("</tr>");
        }
        pw.print("</table>");
        if(pageNow<3){
            pw.print("<a href=httpse?pageNow="+1+"> "+1+" </a>");
            pw.print("<a href=httpse?pageNow="+2+"> "+2+" </a>");
            pw.print("<a href=httpse?pageNow="+3+"> "+3+" </a>");
            pw.print("<a href=httpse?pageNow="+4+"> "+4+" </a>");
            pw.print("<a href=httpse?pageNow="+5+"> "+5+" </a>");
        }else if(pageNow < pageNomber - 2){
            for(int i=pageNow-2;i<pageNomber+1&&i<pageNow+3;i++) {
                pw.print("<a href=httpse?pageNow="+i+"> "+i+" </a>");
            }
        }else {
            pw.print("<a href=httpse?pageNow="+(pageNomber-4)+"> "+(pageNomber-4)+" </a>");
            pw.print("<a href=httpse?pageNow="+(pageNomber-3)+"> "+(pageNomber-3)+" </a>");
            pw.print("<a href=httpse?pageNow="+(pageNomber-2)+"> "+(pageNomber-2)+" </a>");
            pw.print("<a href=httpse?pageNow="+(pageNomber-1)+"> "+(pageNomber-1)+" </a>");
            pw.print("<a href=httpse?pageNow="+pageNomber+"> "+pageNomber+" </a>");
        }
        pw.print("<a href=httpse?pageNow=1>" + " 首页 " + "</a>");
        if(pageNow!=1&&pageNow!=pageNomber) {
            pw.print("<a href=httpse?pageNow=" + (pageNow - 1) + ">" + " 上一页 " + "</a>");
            pw.print("<a href=httpse?pageNow=" + (pageNow + 1) + ">" + " 下一页 " + "</a>");
        }else if(pageNow==1){
            pw.print("<a href=httpse?pageNow=" + (pageNow + 1) + ">" + " 下一页 " + "</a>");
        }else {
            pw.print("<a href=httpse?pageNow=" + (pageNow - 1) + ">" + " 上一页 " + "</a>");
        }
        pw.print("<a href=httpse?pageNow="+pageNomber+">" + " 尾页 " + "</a>");
        pw.print("</center></body>");
        pw.print("</html>");
    }



    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(req.getMethod());
    }
}

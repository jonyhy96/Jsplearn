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
        pw.print("<form action=searchCL method=get>");
        pw.print("查询用户<input type=text name=uName></br>");
        pw.print("<input type=radio name=type value=true  checked>精准查找 ");
        pw.print("<input type=radio name=type value=false>模糊查找 ");
        pw.print("<input type=submit value=查找>");
        pw.print("</form>");
        pw.print("<table border=1>");
        pw.print("<tr bgcolor=pink><th>ID</th><th>Name</th><th>City</th><th>Age</th><th>修改操作</th><th>删除操作</th></tr>");
        for (UserBean u:userBeans
             ) {
            if(userBeans.indexOf(u)==1){
                pw.print("<tr bgcolor=pink>");
            }else{
                pw.print("<tr>");
            }
            pw.print("<td>"+u.getId()+"</td>");
            pw.print("<td>"+u.getName()+"</td>");
            pw.print("<td>"+u.getCity()+"</td>");
            pw.print("<td>"+u.getAge()+"</td>");
            pw.print("<td><a href=updateCL?userId="+u.getId()+">修改用户</a></td>");
            pw.print("<td><a href=deleteCL?userId="+u.getId()+">删除用户</a></td>");
            pw.print("</tr>");
        }
        pw.print("</table>");
        if(pageNow<3){
            pw.print("<a href=admin?pageNow="+1+"> "+1+" </a>");
            pw.print("<a href=admin?pageNow="+2+"> "+2+" </a>");
            pw.print("<a href=admin?pageNow="+3+"> "+3+" </a>");
            pw.print("<a href=admin?pageNow="+4+"> "+4+" </a>");
            pw.print("<a href=admin?pageNow="+5+"> "+5+" </a>");
        }else if(pageNow < pageNomber - 2){
            for(int i=pageNow-2;i<pageNomber+1&&i<pageNow+3;i++) {
                pw.print("<a href=admin?pageNow="+i+"> "+i+" </a>");
            }
        }else {
            pw.print("<a href=admin?pageNow="+(pageNomber-4)+"> "+(pageNomber-4)+" </a>");
            pw.print("<a href=admin?pageNow="+(pageNomber-3)+"> "+(pageNomber-3)+" </a>");
            pw.print("<a href=admin?pageNow="+(pageNomber-2)+"> "+(pageNomber-2)+" </a>");
            pw.print("<a href=admin?pageNow="+(pageNomber-1)+"> "+(pageNomber-1)+" </a>");
            pw.print("<a href=admin?pageNow="+pageNomber+"> "+pageNomber+" </a>");
        }
        pw.print("<a href=admin?pageNow=1>" + " 首页 " + "</a>");
        if(pageNow!=1&&pageNow!=pageNomber) {
            pw.print("<a href=admin?pageNow=" + (pageNow - 1) + ">" + " 上一页 " + "</a>");
            pw.print("<a href=admin?pageNow=" + (pageNow + 1) + ">" + " 下一页 " + "</a>");
        }else if(pageNow==1){
            pw.print("<a href=admin?pageNow=" + (pageNow + 1) + ">" + " 下一页 " + "</a>");
        }else {
            pw.print("<a href=admin?pageNow=" + (pageNow - 1) + ">" + " 上一页 " + "</a>");
        }
        pw.print("<a href=admin?pageNow="+pageNomber+">" + " 尾页 " + "</a></br>");
        pw.print("<form action=admin method=get>");
        pw.print("跳转到<input type=text name=pageNow>页");
        pw.print("<input type=submit value=go></form>");
        pw.print("</center></body>");
        pw.print("</html>");
    }



    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(req.getMethod());
    }
}

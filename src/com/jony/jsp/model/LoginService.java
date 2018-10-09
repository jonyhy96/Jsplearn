package com.jony.jsp.model;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginService extends HttpServlet {
    private AtomicInteger count = new AtomicInteger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username.isEmpty()&&password.isEmpty()){
           resp.sendRedirect("index");
        }
        if(new UserBeanService().check(username,password)){
            HttpSession session = req.getSession();
            session.setAttribute("user",username);
            this.getServletContext().setAttribute("count",count.getAndAdd(1));
            resp.sendRedirect("menu");
        }else{
            resp.sendRedirect("index");
        }
    }
}

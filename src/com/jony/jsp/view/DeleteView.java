package com.jony.jsp.view;

import com.jony.jsp.model.UserBeanService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        UserBeanService userBeanService = new UserBeanService();
        if(userBeanService.deleteUser(userId)){
            resp.sendRedirect("callback?operation=delete&status=success");
        }else{
            resp.sendRedirect("callback?operation=delete&status=failed");
        }
    }
}

package com.cqupt.Servlet;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqupt.bean.User;
import com.cqupt.bean.UserLogin;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginServlet() { }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        UserLogin userLogin = new UserLogin();
        boolean result = userLogin.loginCheck(user);// userLogin用来检查user是否是一个合法登录用户
        System.out.print("连接启动动动动动动动动动动动动动动动动动动动动动动动动动！\n");
        System.out.print(result);
        System.out.print("\n打印结果后结束关闭数据库连接关闭数据库连接关闭数据库连接\n");
        userLogin.close();// 关闭数据库连接

        //控制器进行视图的切换
        if(result) {//登录成功
            RequestDispatcher rd = this.getServletConfig().getServletContext().getRequestDispatcher("/blog.html");
            rd.forward(request, response);//服务器端跳转，跳转到登录成功页
        }
        else {//登录失败
            response.sendRedirect("login.html");//跳转到登录页
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
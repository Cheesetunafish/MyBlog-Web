package com.cqupt.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.cqupt.model.Usr;
import com.cqupt.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private  final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8"); //针对请求进行设置，使用utf8格式来解析请求；
        resp.setCharacterEncoding("utf8"); //针对响应进行设置，构造数据时，按照utf8来构造；
        //1、获取到请求中的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.print("\n获取用户名,username:" + username);
        System.out.print("\n获取密码,password:" + password + "\n");
        if (username == null || "".equals(username) || password == null || "".equals(password)){
            //请求的内容缺失，肯定是登陆失败！
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("用户名或者密码为空，请重新输入！");
            return;
        }
        //2、和数据库中的数据比较
        UserDao userDao = new UserDao();
        Usr user = userDao.selectByName(username);
        if (user == null || !user.getPassword().equals(password)){
            //用户不存在或者密码不正确，也是登陆失败！
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("用户名或者密码错误，请重新输入！");
            return;
        }
        //3、如果比较通过就创建会话
        HttpSession session = req.getSession(true);
        //  把刚才的用户信息存储到会话中：
        session.setAttribute("user",user);

        //4、返回一个重定向报文，跳转到博客列表页：
        resp.sendRedirect("blog.html");
    }

    //这个方法用来检测当前的登陆状态：
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf8");
        HttpSession session = req.getSession(false);
        //检测会话是否存在：
        if (session == null){
            //不存在说明未登录
            Usr user = new Usr();
            resp.getWriter().write(objectMapper.writeValueAsString(user));
            return;
        }
        Usr user = (Usr) session.getAttribute("user");
        if (user == null){
            //虽然有会话，但是会话没有user对象，也视为未登录
            user = new Usr();
            resp.getWriter().write(objectMapper.writeValueAsString(user));
            return;
        }
        //若代码运行到这，说明已经是登陆状态：
        //注意：此处不应该把密码返回到前端，不然会泄露。
        user.setPassword("");
        resp.getWriter().write(objectMapper.writeValueAsString(user));
    }
}

/*
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

 */
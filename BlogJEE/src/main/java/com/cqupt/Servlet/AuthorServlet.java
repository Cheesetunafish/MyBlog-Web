package com.cqupt.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.cqupt.model.Blog;
import com.cqupt.model.BlogDao;
import com.cqupt.model.Usr;
import com.cqupt.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/authorInfo")
public class AuthorServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf8");
        //通过这个方法，来获取到指定博客的作业信息
        String param = req.getParameter("blogId");
        if (param == null || "".equals(param)){
            //缺少参数
            resp.getWriter().write("{ \"ok\": false,\"reason\": \"参数缺失\" }");
            return;
        }

        //根据当前的blogId在数据库中进行查找，找到对应的Blog对象，再进一步根据Blog对象，找到作者信息：
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(Integer.parseInt(param));
        if (blog == null){ //说明此时要找的博客不存在
            resp.getWriter().write("{ \"ok\": false,\"reason\": \"要查询的博客不存在！\" }");
            return;
        }

        //根据blog对象，查询到用户对象
        UserDao userDao = new UserDao();
        Usr author = userDao.selectById(blog.getUserId());
        if (author == null){
            resp.getWriter().write("{ \"ok\": false,\"reason\": \"要查询的用户不存在！\" }");
            return;
        }

        //把author返回到浏览器这边
        //注意：要把密码给消掉，防止泄露密码
        author.setPassword("");
        resp.getWriter().write(objectMapper.writeValueAsString(author));
    }
}
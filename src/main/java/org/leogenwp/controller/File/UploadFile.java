package org.leogenwp.controller.File;

import org.leogenwp.model.User;
import org.leogenwp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;

public class UploadFile extends HttpServlet {
    UserService userService = new UserService();
    public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        PrintWriter messageWriter = response.getWriter();

        Cookie[] cookies = request.getCookies();
        String login = "login";
        String password = "password";
        Cookie cookieLogin = null;
        Cookie cookiePassword = null;
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(login.equals(c.getName())) {
                    cookieLogin = c;
                }
                if(password.equals(c.getName())) {
                    cookiePassword = c;
                }
            }
        }

        messageWriter.println("<h1>" + request.getParameter("password").isEmpty() + "<h1>");

        if(request.getParameter("login").isEmpty()||request.getParameter("password").isEmpty()){
            messageWriter.println("<h1>" + "Login and Password can't be empty" + "<h1>");
        }
        else if(request.getParameter("login").equals(userService.getByLogin(request.getParameter("login")))) {
            messageWriter.println("<h1>" + "This login has been already created" + "<h1>");
        } else  {
            user.setName(request.getParameter("name"));
            user.setLastName(request.getParameter("lastName"));
            user.setLastName(request.getParameter("login"));
            user.setLastName(request.getParameter("password"));
            userService.save(user);
            messageWriter.println("<h1>" + "User has been created" + "<h1>");
        }

    }
}

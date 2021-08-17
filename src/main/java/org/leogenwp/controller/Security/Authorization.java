package org.leogenwp.controller.Security;

import org.leogenwp.model.User;
import org.leogenwp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;

public class Authorization extends HttpServlet {
    UserService userService = new UserService();
    public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getByLogin(request.getParameter("login"));
        PrintWriter messageWriter = response.getWriter();

        if (user != null && user.getPassword().equals(request.getParameter("password"))) {
            Cookie cookie = new Cookie("login", user.getLogin());
            response.addCookie(cookie);
            cookie = new Cookie("password", user.getPassword());
            response.addCookie(cookie);
            messageWriter.println("<h1>" + "OK" + "<h1>");
        } else {
            messageWriter.println("<h1>" + "Invalid login or password" + "<h1>");
        }

        Cookie[] cookies = request.getCookies();

        for(Cookie c: cookies) {
            System.out.println(c.getName() + " " + c.getValue());
        }

        /*if(request.getParameter("login").isEmpty()||request.getParameter("password").isEmpty()){
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
        }*/

    }
}

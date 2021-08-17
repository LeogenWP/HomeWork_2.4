package org.leogenwp.controller.File;

import org.leogenwp.model.Event;
import org.leogenwp.model.File;
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

        User user = userService.getByLogin(cookieLogin.getValue());
        if (user != null && user.getPassword().equals(request.getParameter("password"))) {
            Event event = new Event();
            File file = new File();
            file.setName(request.getParameter("fileName"));
            event.setFile(file);
            user.addEvent(event);
            userService.update(user);

            messageWriter.println("<h1>" + "OK" + "<h1>");
        } else {
            messageWriter.println("<h1>" + "Invalid login or password" + "<h1>");
        }
    }
}

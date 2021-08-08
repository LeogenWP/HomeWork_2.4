package org.leogenwp.controller.User;

import org.leogenwp.model.User;
import org.leogenwp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class CreateUserServlet extends HttpServlet {
    UserService userService = new UserService();
    public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        PrintWriter messageWriter = response.getWriter();
        user.setName(request.getParameter("name").toString());
        user.setLastName(request.getParameter("lastName").toString());
        user.setLogin(request.getParameter("login").toString());
        user.setPassword(request.getParameter("password").toString());

        if(user.getName().equals(userService.getByLogin(user.getName()).getName())) {
            messageWriter.println("<h1>" + "This login has been already created" + "<h1>");
        } else {
            userService.save(user);
            messageWriter.println("<h1>" + "User has been created" + "<h1>");
        }
    }
}

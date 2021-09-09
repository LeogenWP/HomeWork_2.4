package org.leogenwp.controller;

import org.leogenwp.model.User;
import org.leogenwp.repository.io.JavaIOUserRepository;
import org.leogenwp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {
    UserService userService = new UserService(new JavaIOUserRepository());
    User user = new User();
    PrintWriter messageWriter ;

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageWriter = response.getWriter();

        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("lastName"));

        if(request.getParameter("login").isEmpty()||request.getParameter("password").isEmpty()){
            messageWriter.println("<h1>" + "Login and Password can't be empty" + "<h1>");
        }
        else if(request.getParameter("login").equals(userService.getByLogin(request.getParameter("login")).getLogin())) {
            messageWriter.println("<h1>" + "This login has been already created" + "<h1>");
        } else  {
            user.setName(request.getParameter("name"));
            user.setLastName(request.getParameter("lastName"));
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            userService.save(user);
            messageWriter.println("<h1>" + "User has been created" + "<h1>");
        }

    }

    public void doPut (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageWriter = response.getWriter();
        if(request.getParameter("id")!= null && !request.getParameter("id").isEmpty()) {
            user = userService.getById(Integer.parseInt(request.getParameter("id")));
            if (user.getLogin()!= null) {
                user.setName(request.getParameter("name"));
                user.setLastName(request.getParameter("lastName"));
                user.setLogin(request.getParameter("login"));
                user.setPassword(request.getParameter("password"));
                userService.update(user);
            } else {
                messageWriter.println("<h1>" + "User does not exist" + "<h1>");
            }
        }

    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageWriter = response.getWriter();
        if(request.getParameter("id")!= null || !request.getParameter("id").isEmpty()) {
            List<User> list = userService.getAll();

        } else {
            user = userService.getById(Integer.parseInt(request.getParameter("id")));
            if (user.getLogin()!= null) {
                user.setName(request.getParameter("name"));
                user.setLastName(request.getParameter("lastName"));
                user.setLogin(request.getParameter("login"));
                user.setPassword(request.getParameter("password"));
                messageWriter.println("<h1>" + user.toString() + "<h1>");
            } else {
                messageWriter.println("<h1>" + "User does not exist" + "<h1>");
            }
        }

    }

    public void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageWriter = response.getWriter();
        if(request.getParameter("id")!= null && !request.getParameter("id").isEmpty()) {
            userService.deleteById(Integer.parseInt(request.getParameter("id")));
            messageWriter.println("<h1>" + "User has benn deleted" + "<h1>");
        }
    }


}

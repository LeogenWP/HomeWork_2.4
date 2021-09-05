package org.leogenwp.controller;

import org.leogenwp.model.Event;
import org.leogenwp.model.User;
import org.leogenwp.service.EventService;
import org.leogenwp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/event")
public class EventController extends HttpServlet {
    EventService eventService = new EventService();
    UserService userService = new UserService();
    User user = new User();
    PrintWriter messageWriter ;

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageWriter = response.getWriter();



    }

    public void doPut (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageWriter = response.getWriter();


    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getByLogin(request.getParameter("login"));
        if (user.getLogin() != null && user.getPassword().equals(request.getParameter("password"))) {

            if(request.getParameter("eventId")!= null || !request.getParameter("eventId").isEmpty()) {
                Event event = eventService.getById(Integer.parseInt(request.getParameter("eventId")));
                if(event.getId()!= null) {
                    System.out.println("Ok");
                } else {
                    System.out.println("no id found");
                }
            } else {
                List<Event> events = eventService.getAll();
            }
        } else {
            messageWriter.println("<h1>" + "Invalid login or password" + "<h1>");
        }

    }

    public void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageWriter = response.getWriter();

        User user = userService.getByLogin(request.getParameter("login"));
        if (user.getLogin() != null && user.getPassword().equals(request.getParameter("password"))) {

            if(request.getParameter("eventId")!= null || !request.getParameter("eventId").isEmpty()) {
                eventService.deleteById(Integer.parseInt(request.getParameter("eventId")));
            }
        } else {
            messageWriter.println("<h1>" + "Invalid login or password" + "<h1>");
        }

    }
}

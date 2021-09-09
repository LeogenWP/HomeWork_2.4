package org.leogenwp.controller;

import org.leogenwp.model.Event;
import org.leogenwp.model.File;
import org.leogenwp.model.User;
import org.leogenwp.repository.io.JavaIOFileRepository;
import org.leogenwp.repository.io.JavaIOUserRepository;
import org.leogenwp.service.FileService;
import org.leogenwp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet("/file")
public class FileController extends HttpServlet {
    FileService fileService = new FileService(new JavaIOFileRepository());
    UserService userService = new UserService(new JavaIOUserRepository());
    User user = new User();
    PrintWriter messageWriter ;

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        messageWriter = response.getWriter();

        User user = userService.getByLogin(request.getParameter("login"));
        if (user.getLogin() != null && user.getPassword().equals(request.getParameter("password"))) {
            Event event = new Event();
            File file = new File();
            event.setUploaded(new Date());
            file.setAdress(request.getParameter("fileAdress"));
            event.setFile(file);
            user.addEvent(event);
            userService.update(user);

            messageWriter.println("<h1>" + "OK" + "<h1>");
        } else {
            messageWriter.println("<h1>" + "Invalid login or password" + "<h1>");
        }

    }

    public void doPut (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageWriter = response.getWriter();

        User user = userService.getByLogin(request.getParameter("login"));
        if (user.getLogin() != null && user.getPassword().equals(request.getParameter("password"))) {
            File file = fileService.getById(Integer.parseInt(request.getParameter("fileId")));
            if(file.getId()!=null) {
                file.setAdress(request.getParameter("fileAdress"));
            } else {
                messageWriter.println("<h1>" + "no such fileId" + "<h1>");
            }
            fileService.update(file);

            messageWriter.println("<h1>" + "OK" + "<h1>");
        } else {
            messageWriter.println("<h1>" + "Invalid login or password" + "<h1>");
        }
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageWriter = response.getWriter();

        User user = userService.getByLogin(request.getParameter("login"));
        if (user.getLogin() != null && user.getPassword().equals(request.getParameter("password"))) {

            if(request.getParameter("fileId")!= null || !request.getParameter("fileId").isEmpty()) {
                File file = fileService.getById(Integer.parseInt(request.getParameter("fileId")));
                if(file.getId()!=null) {
                    messageWriter.println("<h1>" + file + "<h1>");
                } else {
                    messageWriter.println("<h1>" + "no such fileId" + "<h1>");
                }
                messageWriter.println("<h1>" + "OK" + "<h1>");
            } else {
                List<File> list = fileService.getAll();
                }
            } else {
            messageWriter.println("<h1>" + "Invalid login or password" + "<h1>");
        }

    }

    public void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messageWriter = response.getWriter();

        User user = userService.getByLogin(request.getParameter("login"));
        if (user.getLogin() != null && user.getPassword().equals(request.getParameter("password"))) {

            if(request.getParameter("fileId")!= null || !request.getParameter("fileId").isEmpty()) {
                fileService.deleteById(Integer.parseInt(request.getParameter("fileId")));
            }
        } else {
            messageWriter.println("<h1>" + "Invalid login or password" + "<h1>");
        }
    }
}

package org.leogenwp.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.leogenwp.service.UserService;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class UserControllerTest  {
    UserService userService = mock(UserService.class);
    UserController userControllerUnderTest = new UserController(userService);


   /* @Before
    public void setUp() {
        userController = new UserController();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }*/

/*
    @Test
    void whenLoginOrPasswordEmpty() throws Exception {
        request.addParameter("login", "");
        request.addParameter("password", "");

        userController.doPost(request, response);
        assertEquals(5,5);
       // assertEquals("text/html", response.getContentType());
    }
*/
    @Test
    public void whenLoginOrPasswordEmpty() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn("");

        userControllerUnderTest.doPost(request,response);
        //verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
        //System.out.println(response.getWriter().toString());
        //assertTrue();
    }
    @Test
    void doPut() {
    }

    @Test
    void doGet() {
    }

    @Test
    void doDelete() {
    }
}
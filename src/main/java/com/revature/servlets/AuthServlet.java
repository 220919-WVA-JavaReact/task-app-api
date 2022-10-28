package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.util.exceptions.LoginException;
import com.revature.util.exceptions.UserNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


public class AuthServlet extends HttpServlet {

    private AuthService as = new AuthService();
    private ObjectMapper om = new ObjectMapper();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        // Totally valid to create a custom DTO to represent a Credentials request body
        // but HashMaps work too.
        HashMap<String, Object> credentials = om.readValue(req.getInputStream(), HashMap.class);
        String username = (String) credentials.get("username");
        String password = (String) credentials.get("password");

        try {
            User principal = as.login(username, password);

            // HttpSession allows us to persist data across multiple requests
            HttpSession session = req.getSession();
            session.setAttribute("userId", principal.getId());
            session.setAttribute("userRole", principal.getRole());

            // To make Chrome work with our cookie
            String cookie = res.getHeader("Set-Cookie") + "; SameSite=None; Secure";
            res.setHeader("Set-Cookie", cookie);

            UserDTO principalDTO = new UserDTO(principal);
            try(PrintWriter pw = res.getWriter()){
                System.out.println("session userRole: " + session.getAttribute("userRole"));
                pw.write(om.writeValueAsString(principalDTO));
                res.setContentType("application/json");
                res.setStatus(200);
            }

        } catch (UserNotFoundException | LoginException e) {
            res.sendError(400, "Login unsuccessful.");
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        HttpSession session = req.getSession(false);

        if(session != null){
            session.invalidate();

        }

        res.setStatus(204);
    }
}

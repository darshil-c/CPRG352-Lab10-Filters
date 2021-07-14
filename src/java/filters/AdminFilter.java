/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;

/**
 *
 * @author Chaudhari
 */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpSession session = httpRequest.getSession();
        String email = (String) session.getAttribute("email");
        
        UserDB udb = new UserDB();
        User user = udb.get(email);
        
        Role role = user.getRole();
        int roleID = role.getRoleId();
        
        System.out.print(roleID);
        
        if (roleID == 1) {
            httpResponse.sendRedirect("admin");
            return;
        } else if (roleID == 2) {
            httpResponse.sendRedirect("notes");
            return;
        } else if (roleID != 1 || roleID != 2) {
            httpResponse.sendRedirect("login");
            return;
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
    

}

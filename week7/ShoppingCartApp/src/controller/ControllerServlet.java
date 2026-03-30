package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;   // For ResultSet
import dao.UserDAO; // Make sure UserDAO is in dao package

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if("register".equals(action)) {
            req.getRequestDispatcher("/register.html").forward(req,res);
        } else if("login".equals(action)) {
            req.getRequestDispatcher("/login.html").forward(req,res);
        } else if("catalog".equals(action)) {
            req.getRequestDispatcher("/catalog.jsp").forward(req,res);
        } else if("cart".equals(action)) {
            req.getRequestDispatcher("/cart.jsp").forward(req,res);
        } else {
            res.sendRedirect("/ShoppingCartApp/register.html");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        String action = req.getParameter("action");
        if(action == null) { res.sendRedirect("register.html"); return; }

        try {
            if("register".equals(action)) {
                UserDAO.register(
                    req.getParameter("name"),
                    req.getParameter("email"),
                    req.getParameter("password")
                );
                res.sendRedirect("login.html");
            } else if("login".equals(action)) {
                ResultSet rs = UserDAO.login(
                    req.getParameter("email"),
                    req.getParameter("password")
                );

                if(rs.next()) {
                    req.getSession().setAttribute("userId", rs.getInt("id"));
                    res.sendRedirect("catalog.jsp");
                } else {
                    res.sendRedirect("login.html?error=1");
                }
            }

        } catch(Exception e) { e.printStackTrace(); }
    }
}

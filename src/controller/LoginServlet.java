package controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import org.pmw.tinylog.Logger;

public class LoginServlet extends HttpServlet {
	
	

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		
		try {
			String username = req.getParameter("username").trim();
			String password = req.getParameter("password").trim();
		
			
			User u = em.find(User.class, username);
			
			if (u == null) {
				
				resp.sendRedirect("login.jsp");
			} else if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				
				HttpSession session = req.getSession(true);
				session.setAttribute("user", u);
				resp.sendRedirect(req.getContextPath() + "/loggedin.jsp");
			} else {
				
				resp.sendRedirect("login.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

}

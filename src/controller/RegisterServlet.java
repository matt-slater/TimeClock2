package controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;



public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		
		try {
			User u = new User();
			u.setUsername(request.getParameter("username"));
			u.setPassword(request.getParameter("password"));
			u.setEmail(request.getParameter("email"));
			u.setfName(request.getParameter("firstname"));
			u.setlName(request.getParameter("lastname"));
			u.setWage((Integer.parseInt(request.getParameter("wage")))*100);
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			
			request.getRequestDispatcher("success.html").forward(request, response);;
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

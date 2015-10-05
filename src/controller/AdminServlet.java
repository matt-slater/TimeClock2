package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Shift;
import model.User;

public class AdminServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		
		Timestamp t = new Timestamp(new Date().getTime());
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u", User.class);
		List<User> results = q.getResultList();
		req.setAttribute("users", results);
		
		TypedQuery<Shift> p = em.createQuery("SELECT u FROM Shift u WHERE u.clockIn IS NOT NULL AND u.clockOut IS NULL", Shift.class);
		List<Shift> results2 = p.getResultList();
		req.setAttribute("currentShifts", results2);
		req.setAttribute("time", t);
		RequestDispatcher dispatcher = req.getRequestDispatcher("admin.jsp");
		dispatcher.forward(req,resp);

		
	}
}

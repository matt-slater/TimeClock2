package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Shift;
import model.User;

import org.pmw.tinylog.Logger;

public class ShiftManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (req.getSession().getAttribute("user") != null) {
			
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();
			Date d = new Date();
			Timestamp t = new Timestamp(d.getTime());
			User u = (User) req.getSession().getAttribute("user");
			User dbuser = em.find(User.class, u.getUsername());
			em.getTransaction().begin();

			if (dbuser.getCurrentShift() == null) {
				//user is clocked out.
				Shift s = new Shift();
				s.setClockIn(t);
				s.setUser(dbuser);
				dbuser.setCurrentShift(s);
				Logger.info(dbuser.getUsername() +" clocked in at: " + t);

			} else if (dbuser.getCurrentShift() != null) {
				//user is clocked in.
				Shift s = dbuser.getCurrentShift();
				s.setClockOut(t);
				dbuser.setCurrentShift(null);
				Logger.info(dbuser.getUsername() +" clocked out at: " + t);
			}
			
			em.getTransaction().commit();
			resp.sendRedirect("loggedin.jsp");
		} else {
			resp.sendError(501);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}

package listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.objectdb.Enhancer;

public class UserListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		EntityManagerFactory emf = (EntityManagerFactory)e.getServletContext().getAttribute("emf");
		emf.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		Enhancer.enhance("model.*");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/user.odb");
		e.getServletContext().setAttribute("emf", emf);
	}

}

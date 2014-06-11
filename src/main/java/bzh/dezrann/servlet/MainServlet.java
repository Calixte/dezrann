package bzh.dezrann.servlet;

import bzh.dezrann.Sessions;
import bzh.dezrann.recording.databean.Recording;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Singleton
public class MainServlet extends HttpServlet {

	@Inject
	private Sessions sessions;
	@Inject
	private EntityManager entityManager;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Query query = entityManager.createQuery("from Recording", Recording.class);
		List<Recording> recordings = query.getResultList();

		req.setAttribute("sessions", sessions);
		req.setAttribute("recordings", recordings);
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}

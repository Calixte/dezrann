package bzh.dezrann.servlet;

import bzh.dezrann.recording.databean.Recording;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;

@Singleton
public class ReplayServlet extends HttpServlet {

	@Inject
	private EntityManager entityManager;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String recordingId = req.getParameter("id");
		req.setAttribute("recordingId", recordingId);
		getServletContext().getRequestDispatcher("/replay.jsp").forward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer recordingId = Integer.parseInt(req.getParameter("id"));
		Recording recording = entityManager.find(Recording.class, recordingId);
		entityManager.getTransaction().begin();
		entityManager.remove(recording);
		entityManager.getTransaction().commit();
	}
}

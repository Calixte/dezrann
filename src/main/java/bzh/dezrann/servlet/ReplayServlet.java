package bzh.dezrann.servlet;

import bzh.dezrann.recording.databean.Record;
import bzh.dezrann.recording.databean.Recording;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ReplayServlet extends HttpServlet {

	@Inject
	private EntityManager entityManager;
	@Inject
	private Gson gson;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String recordingId = req.getParameter("id");
		req.setAttribute("recordingId", recordingId);
		Query query = entityManager.createQuery("from Record where recordingId = :recordingId order by timestamp", Record.class);
		query.setParameter("recordingId", Integer.parseInt(recordingId));
		List<Record> records = query.getResultList();
		List<String> jsonRecords = records.stream().map(Record::getJson).collect(Collectors.toList());
		req.setAttribute("records", gson.toJson(jsonRecords));
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

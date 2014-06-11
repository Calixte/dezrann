package bzh.dezrann.servlet;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;

@Singleton
public class ReplayServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String recordingId = req.getParameter("id");
		req.setAttribute("recordingId", recordingId);
		getServletContext().getRequestDispatcher("/replay.jsp").forward(req, resp);
	}
}

package bzh.dezrann.servlet;

import bzh.dezrann.Message;
import bzh.dezrann.Sessions;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;

@Singleton
public class WatchServlet extends HttpServlet {

	@Inject
	private Sessions sessions;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Session session = sessions.get(id);
		if (session != null) {
			req.setAttribute("session", session);
			getServletContext().getRequestDispatcher("/watch.jsp").forward(req, resp);
		} else {
			req.getSession().setAttribute("error", "session not live");
			resp.sendRedirect(getServletContext().getContextPath());
		}
	}
}

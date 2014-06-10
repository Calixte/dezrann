package bzh.dezrann.servlet;

import bzh.dezrann.Sessions;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class MainServlet extends HttpServlet {

	@Inject
	private Sessions sessions;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("sessions", sessions);
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}

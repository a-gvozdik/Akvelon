package akvelon.artemgvozdik.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import akvelon.artemgvozdik.bean.User;
import akvelon.artemgvozdik.dao.UserDAO;
import akvelon.artemgvozdik.handler.user.DeleteUserHandler;

public class MainActionHandler extends Handler {
	static Logger logger = Logger.getLogger(MainActionHandler.class);

	@Override
	public void doAction(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession sess = req.getSession();
		sess.setAttribute("checked", true);
		if (req.getParameter("mainbutton").equals("Edit")) {
			String select[] = req.getParameterValues("maincheck");

			if (select != null && select.length > 1) {
				RequestDispatcher rd = req.getRequestDispatcher("/");
				sess.setAttribute("checked", false);
				try {
					rd.forward(req, resp);
				} catch (ServletException e) {
					logger.error("Exception ", e);
				} catch (IOException e) {
					logger.error("Exception ", e);
				}
			}
			
			if (select != null && select.length == 1){
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/updateUser.jsp");
				User user = new User ();
				UserDAO udao = new UserDAO();
				user = udao.getUser(Integer.parseInt(select[0]));
				req.setAttribute("user", user);
				try {
					rd.forward(req, resp);
				} catch (ServletException e) {
					logger.error("Exception ", e);
				} catch (IOException e) {
					logger.error("Exception ", e);
				}
			} 
			
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/addUser.jsp");
			try {
				rd.forward(req, resp);
			} catch (ServletException e) {
				logger.error("Exception ", e);
			} catch (IOException e) {
				logger.error("Exception ", e);
			}
			
		}

		if (req.getParameter("mainbutton").equals("Delete")) {
			new DeleteUserHandler().doAction(req, resp);
		}

		if (req.getParameter("mainbutton").equals("Refresh")) {
			UserDAO udao = new UserDAO();
			List <User> ulist = udao.getAll();
			sess.setAttribute("ulist", ulist);
			RequestDispatcher rd = req.getRequestDispatcher("/");
			try {
				rd.forward(req, resp);
			} catch (ServletException e) {
				logger.error("Exception ", e);
			} catch (IOException e) {
				logger.error("Exception ", e);
			}
		}

	}

}

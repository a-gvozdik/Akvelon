package akvelon.artemgvozdik.handler.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import akvelon.artemgvozdik.bean.User;
import akvelon.artemgvozdik.dao.UserDAO;
import akvelon.artemgvozdik.handler.Handler;

public class GetAllUsersHandler extends Handler {
	static Logger logger = Logger.getLogger(GetAllUsersHandler.class);

	@Override
	public void doAction(HttpServletRequest req, HttpServletResponse resp) {
		List <User> ulist = new ArrayList <User> ();
		UserDAO udao = new UserDAO ();
		ulist = udao.getAll();
		req.setAttribute("ulist", ulist);
		RequestDispatcher rd = req.getRequestDispatcher("/main.jsp");
	    try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			logger.error("Exception ", e);
		} catch (IOException e) {
			logger.error("Exception ", e);
		}

	}

}

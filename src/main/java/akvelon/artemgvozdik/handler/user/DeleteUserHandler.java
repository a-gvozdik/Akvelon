package akvelon.artemgvozdik.handler.user;

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
import akvelon.artemgvozdik.handler.Handler;

public class DeleteUserHandler extends Handler {
	static Logger logger = Logger.getLogger(DeleteUserHandler.class);

	@Override
	public void doAction(HttpServletRequest req, HttpServletResponse resp) {
		UserDAO udao = new UserDAO();
		String select[] = req.getParameterValues("maincheck");
		if (select != null && select.length>0) {
			
			for (String i: select) {
				udao.delete(Integer.parseInt(i));
			}
		}
		HttpSession sess = req.getSession();
		List <User> ulist = udao.getAll();
		sess.setAttribute("ulist", ulist);
		RequestDispatcher rd = req
				.getRequestDispatcher("/");
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			logger.error("Exception ", e);
		} catch (IOException e) {
			logger.error("Exception ", e);
		}


	}

}

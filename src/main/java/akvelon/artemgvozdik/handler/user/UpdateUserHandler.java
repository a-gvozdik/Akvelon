package akvelon.artemgvozdik.handler.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class UpdateUserHandler extends Handler {
	static Logger logger = Logger.getLogger(UpdateUserHandler.class);

	@Override
	public void doAction(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getParameter("submit") != null) {
			User user = new User();
			UserDAO udao = new UserDAO();
			user.setId(Integer.parseInt(req.getParameter("id")));
			user.setFname(req.getParameter("fname"));
			user.setLname(req.getParameter("lname"));
			user.setLogin(req.getParameter("login"));
			user.setBalance(Double.parseDouble(req.getParameter("balance")));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateInString = req.getParameter("birthday");
			Date birthday = null;
			try {
				birthday = sdf.parse(dateInString);
			} catch (ParseException e) {
				logger.error("Exception ", e);
			}
			user.setBirthday(birthday);
			udao.update(user);
			HttpSession sess = req.getSession();
			List<User> ulist = udao.getAll();
			sess.setAttribute("ulist", ulist);
			RequestDispatcher rd = req.getRequestDispatcher("/");
			try {
				rd.forward(req, resp);
			} catch (ServletException e) {
				logger.error("Exception. Submit ", e);
			} catch (IOException e) {
				logger.error("Exception. Submit ", e);
			}
		}
		
		if (req.getParameter("updatebutton") != null) {
			UserDAO udao = new UserDAO();
			List <User> ulist = udao.getAll();
			HttpSession sess = req.getSession();
			sess.setAttribute("ulist", ulist);
			RequestDispatcher rd = req.getRequestDispatcher("/");
			try {
				rd.forward(req, resp);
			} catch (ServletException e) {
				logger.error("Exception. Cancel ", e);
			} catch (IOException e) {
				logger.error("Exception. Cancel ", e);
			}
		}

	}

}

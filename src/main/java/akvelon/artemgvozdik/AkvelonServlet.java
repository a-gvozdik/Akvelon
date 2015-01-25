package akvelon.artemgvozdik;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import akvelon.artemgvozdik.handler.Handler;
import akvelon.artemgvozdik.handler.LoginValid;
import akvelon.artemgvozdik.handler.MainActionHandler;
import akvelon.artemgvozdik.handler.user.AddUserHandler;
import akvelon.artemgvozdik.handler.user.UpdateUserHandler;


public class AkvelonServlet extends HttpServlet {
	static Logger logger = Logger.getLogger(AkvelonServlet.class);

	private Map<String, Handler> urls;
	
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		super.init();
		logger.info("init method started");
		urls = new HashMap<String, Handler>();
		urls.put("/AkvelonServlet/action", new MainActionHandler());
		urls.put("/AkvelonServlet/updateUser", new UpdateUserHandler());
		urls.put("/AkvelonServlet/addUser", new AddUserHandler());
		urls.put("/AkvelonServlet/checkLogin", new LoginValid());
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		urls.get(req.getRequestURI()).doAction(req, resp);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

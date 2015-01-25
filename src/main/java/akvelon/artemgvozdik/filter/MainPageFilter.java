package akvelon.artemgvozdik.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import akvelon.artemgvozdik.bean.User;
import akvelon.artemgvozdik.dao.UserDAO;

public class MainPageFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain fc) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if (request.getRequestURI().equals("/AkvelonServlet/")) {
		HttpSession session = request.getSession();
		List <User> ulist = new ArrayList <User> ();
		UserDAO udao = new UserDAO ();
		ulist = udao.getAll();
		session.setAttribute("ulist", ulist);
		}
		fc.doFilter(request, resp);
		
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

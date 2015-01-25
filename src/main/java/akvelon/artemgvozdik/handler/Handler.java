package akvelon.artemgvozdik.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Handler {
	abstract public void doAction(HttpServletRequest req,
			HttpServletResponse resp);
}

package by.htp.ishop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.htp.ishop.controller.command.Command;
import by.htp.ishop.controller.command.CommandException;
import by.htp.ishop.controller.command.CommandProvider;
import by.htp.ishop.dao.connection_pool.ConnectionPool;
import by.htp.ishop.dao.connection_pool.ConnectionPoolException;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CommandProvider provider = new CommandProvider();
	private final static Logger log = Logger.getLogger(Controller.class);
	private final static String ERROR_PAGE = "error.jsp";

    public Controller() {
    }

	@Override
    public void init() throws ServletException {
		try {ConnectionPool cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
		} catch (ConnectionPoolException e) {
			log.error("Exception while ConnectionPool is initialized", e);
		}
    }

    @Override
    public void destroy() {
    	ConnectionPool.getInstance().dispose();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String commandName = req.getParameter("command");
		Command executionCommand = provider.getCommand(commandName);
		try {
			log.debug(commandName);
			executionCommand.execute(req, resp);
		} catch (CommandException e) {
			log.error(e.getMessage(), e);
			req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
		}
    }

}

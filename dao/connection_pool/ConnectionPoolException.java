package by.htp.ishop.dao.connection_pool;

public class ConnectionPoolException extends Exception{
	public static final long serialVersionUID = 1L;
	
	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
}

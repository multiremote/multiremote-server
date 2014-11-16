package net.multiremote.server.service;

import net.multiremote.server.data.UserEO;
import net.multiremote.server.data.type.LoginResult;


/**
 *
 * @author gaetan
 */
public interface SecurityService {

	UserEO getCurrentUser();
	
	Integer getCurrentUserId();
	
	Boolean isAuthenticated();
	
	LoginResult login(String login, String password);
	
	void logout();
	
}

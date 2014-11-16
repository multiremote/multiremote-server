package net.multiremote.server.service.impl;

import net.multiremote.server.dao.UserDao;
import net.multiremote.server.data.UserEO;
import net.multiremote.server.data.type.LoginResult;
import net.multiremote.server.service.AbstractServiceImpl;
import net.multiremote.server.service.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gaetan
 */
@Service
@Transactional
public class SecurityServiceImpl extends AbstractServiceImpl implements SecurityService {

	private static final Logger LOG = Logger.getLogger(SecurityServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationProvider authManager;

	@Override
	public UserEO getCurrentUser() {
		Integer id = getCurrentUserId();
		return userDao.getById(id).get();
	}

	@Override
	public Integer getCurrentUserId() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return -1;
		}
		return Integer.parseInt(context.getAuthentication().getName());
	}

	@Override
	public Boolean isAuthenticated() {
		SecurityContext context = SecurityContextHolder.getContext();
		return context != null && context.getAuthentication() != null;
	}

	@Override
	public LoginResult login(String login, String password) {
		if(isAuthenticated()){
			return LoginResult.ALREADY_CONNECTED;
		}
		try {
			Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
			SecurityContextHolder.getContext().setAuthentication(auth);
			return LoginResult.OK;
		}catch(BadCredentialsException ex){
			LOG.warn("Wrong password for login "+login);
			return LoginResult.WRONG_PASSWORD;
		}catch(UsernameNotFoundException ex){
			LOG.warn("Login "+login+" not found");
			return LoginResult.UNKNOWN_USERNAME;
		} catch (AuthenticationException ex) {
			LOG.error(ex);
			return LoginResult.INTERNAL_ERROR;
		}
	}

	@Override
	public void logout() {
		SecurityContextHolder.clearContext();
	}
}

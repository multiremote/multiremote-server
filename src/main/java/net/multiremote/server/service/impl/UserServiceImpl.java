package net.multiremote.server.service.impl;

import java.util.List;
import net.multiremote.server.dao.UserDao;
import net.multiremote.server.data.UserEO;
import net.multiremote.server.service.AbstractServiceImpl;
import net.multiremote.server.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gaetan
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl implements UserService {
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserEO findByLogin(String login){
		if(login==null || login.isEmpty()){
			return null;
		}
		return userDao.getByLogin(login);
	}

	@Override
	public List<UserEO> getAllUsers() {
		return userDao.getAllUsers();
	}

}

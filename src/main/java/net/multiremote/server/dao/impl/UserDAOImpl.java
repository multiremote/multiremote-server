package net.multiremote.server.dao.impl;

import java.util.List;
import net.multiremote.server.dao.UserDao;
import net.multiremote.server.data.UserEO;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gaetan
 */
@Repository
public class UserDAOImpl extends AbstractDAO<UserEO> implements UserDao {

	private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl() {
		super(UserEO.class);
	}

	@Override
	public UserEO getByLogin(String login) {
		Query query = sessionFactory.getCurrentSession().createQuery(UserEO.FIND_BY_LOGIN);
		query.setParameter(UserEO.PARAM_LOGIN, login);
		return (UserEO) query.uniqueResult();
	}

	@Override
	public List<UserEO> getAllUsers() {
		return findAllEntities();
	}

	@Override
	public UserEO getById(Integer id) {
		return super.getById(id);
	}
}

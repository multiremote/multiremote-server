package net.multiremote.server.dao.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import net.multiremote.server.dao.UserDao;
import net.multiremote.server.data.UserEO;
import net.multiremote.server.data.root.Users;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gaetan
 */
@Repository
public class UserDAOImpl extends AbstractDAO<UserEO> implements UserDao {

	private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);

	//@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl() {
		super(UserEO.class);
	}

	@Override
	public Optional<UserEO> getByLogin(String login) {
		if(login==null){
			return Optional.empty();
		}
		return getAllUsers().stream().filter(u -> login.equalsIgnoreCase(u.getLogin())).findFirst();
	}

	@Override
	public List<UserEO> getAllUsers() {
		try {
			JAXBContext context = JAXBContext.newInstance(Users.class);
			Unmarshaller um = context.createUnmarshaller();
			Users users = (Users) um.unmarshal(new FileReader("config/users.xml"));
			return users.getUsers();
		} catch (JAXBException | FileNotFoundException ex) {
			LOG.error("Cannot read users", ex);
		}
		return new ArrayList<>();
	}
}

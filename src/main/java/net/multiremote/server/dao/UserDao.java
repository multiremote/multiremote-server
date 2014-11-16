package net.multiremote.server.dao;

import java.util.List;
import net.multiremote.server.data.UserEO;
/**
 *
 * @author gaetan
 */
public interface UserDao {

	UserEO getById(Integer id);
	UserEO getByLogin(String login);
	List<UserEO> getAllUsers();
}

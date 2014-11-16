package net.multiremote.server.dao;

import java.util.List;
import java.util.Optional;
import net.multiremote.server.data.UserEO;
/**
 *
 * @author gaetan
 */
public interface UserDao {

	Optional<UserEO> getById(Integer id);
	Optional<UserEO> getByLogin(String login);
	List<UserEO> getAllUsers();
}

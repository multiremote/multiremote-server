package net.multiremote.server.service;

import java.util.List;
import java.util.Optional;
import net.multiremote.server.data.UserEO;

/**
 *
 * @author gaetan
 */
public interface UserService {

	Optional<UserEO> findByLogin(String login);

	List<UserEO> getAllUsers();

}

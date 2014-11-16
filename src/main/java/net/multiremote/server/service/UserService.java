package net.multiremote.server.service;

import java.util.List;
import net.multiremote.server.data.UserEO;

/**
 *
 * @author gaetan
 */
public interface UserService {

	UserEO findByLogin(String login);

	List<UserEO> getAllUsers();

}

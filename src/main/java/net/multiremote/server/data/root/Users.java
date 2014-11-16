package net.multiremote.server.data.root;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import net.multiremote.server.data.UserEO;

/**
 *
 * @author Gaétan
 */
@XmlRootElement(name = "users")
public class Users {
	private List<UserEO> users;

	public List<UserEO> getUsers() {
		return users;
	}

	public void setUsers(List<UserEO> users) {
		this.users = users;
	}
}

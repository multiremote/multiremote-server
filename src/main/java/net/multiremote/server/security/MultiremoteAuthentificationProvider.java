package net.multiremote.server.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import net.multiremote.server.data.UserEO;
import net.multiremote.server.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Gaétan
 */
public class MultiremoteAuthentificationProvider implements AuthenticationProvider {

	private static final Logger LOG = Logger.getLogger(MultiremoteAuthentificationProvider.class);

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String login = authentication.getName();
		String password = authentication.getCredentials().toString();
		Optional<UserEO> user;

		try {
			user = userService.findByLogin(login);
		} catch (Exception ex) {
			LOG.error("Cannot get user with login "+login, ex);
			throw new AuthenticationServiceException("Cannot get user with login " + login, ex);
		}
		if (user.isPresent()) {

			//FIXME test mdp
			String passwordHashed = password;
			if (user.get().getPassword().equals(passwordHashed)) {

				Set<GrantedAuthority> roles = new HashSet<>();
				List<String> groupsStr = new ArrayList<>();
				List<String> rolesStr = new ArrayList<>();
				//FIXME no group, just role
//				user.getGroups()
//						.forEach(g -> {
//							groupsStr.add(g.getTechnicalname());
//							g.getRoles()
//							.forEach(r -> {
//								roles.add(new SimpleGrantedAuthority(r.getTechnicalname()));
//								rolesStr.add(r.getTechnicalname());
//							});
//						});
				LOG.info("Authentification success for user=" + login + ", groups=" + groupsStr + ", roles=" + rolesStr);
				//FIXME audit this

				return new UsernamePasswordAuthenticationToken(user.get().getUserId(), password, roles);
			} else {
				throw new BadCredentialsException("wrong password");
			}
		} else {
			throw new UsernameNotFoundException("Username " + login + " not found");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

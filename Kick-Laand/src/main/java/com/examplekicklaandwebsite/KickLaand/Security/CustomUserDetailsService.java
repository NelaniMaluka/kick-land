package com.examplekicklaandwebsite.KickLaand.Security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.Roles.Roles;
import com.examplekicklaandwebsite.KickLaand.User.UserAccount;
import com.examplekicklaandwebsite.KickLaand.User.UserAccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private UserAccountRepository userAccountRepository;
	
	@Autowired
	public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
		super();
		this.userAccountRepository = userAccountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    UserAccount user = userAccountRepository.findByEmail(username);
	    if (user == null) {
	        throw new UsernameNotFoundException("Username not found");
	    }

	    return new User(user.getEmail(), user.getPassword(), mapRolesToAuthoroties(user.getRoles()));
	}

	
	private Collection<GrantedAuthority> mapRolesToAuthoroties(List<Roles> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}

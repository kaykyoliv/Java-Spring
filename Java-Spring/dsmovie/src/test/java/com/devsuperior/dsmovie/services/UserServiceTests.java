package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.projections.UserDetailsProjection;
import com.devsuperior.dsmovie.repositories.UserRepository;
import com.devsuperior.dsmovie.tests.UserDetailsFactory;
import com.devsuperior.dsmovie.tests.UserFactory;
import com.devsuperior.dsmovie.utils.CustomUserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class UserServiceTests {

	@InjectMocks
	private UserService service;
	@Mock
	private UserRepository userRepository;
	@Mock
	private CustomUserUtil userUtil;

	private List<UserDetailsProjection> userDetails;
	private UserEntity user;

	@BeforeEach
	void setUp() throws Exception{
		user = UserFactory.createUserEntity();
		userDetails = UserDetailsFactory.createCustomAdminUser(user.getUsername());
	}

	@Test
	public void authenticatedShouldReturnUserEntityWhenUserExists() {
		Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
		Mockito.when(userUtil.getLoggedUsername()).thenReturn(user.getUsername());

		UserEntity result = service.authenticated();

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), user.getId());
	}

	@Test
	public void authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {
		Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
		Mockito.when(userUtil.getLoggedUsername()).thenReturn(user.getUsername());

		Assertions.assertThrows(UsernameNotFoundException.class, () -> {
			service.authenticated();
		});
	}

	@Test
	public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {
		Mockito.when(userRepository.searchUserAndRolesByUsername(user.getUsername())).thenReturn(userDetails);
		UserDetails result = service.loadUserByUsername(user.getUsername());
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getUsername(), user.getUsername());
	}

	@Test
	public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {
		Mockito.when(userRepository.searchUserAndRolesByUsername(any())).thenReturn(new ArrayList<>());

		Assertions.assertThrows(UsernameNotFoundException.class, () -> {
			service.loadUserByUsername(user.getUsername());
		});
	}
}

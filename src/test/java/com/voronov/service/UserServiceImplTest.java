package com.voronov.service;

import com.voronov.dao.DAOinterfaces.UserDao;
import com.voronov.entities.Role;
import com.voronov.entities.User;
import com.voronov.service.serviceInterfaces.RoleService;
import com.voronov.service.serviceInterfaces.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {
	@InjectMocks
	@Spy
	UserService service = new UserServiceImpl();

	@Mock
	UserDao dao;

	@Mock
	RoleService roleService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void isExists() {
		User user = new User("username", "password", "mail");
		service.isExists(user);
		verify(dao, times(1)).isExists(user.getUsername(), user.getMail());
	}

	@Test
	public void addUser() {
		User user = new User("username", "password", "mail");
		doReturn(false).when(service).isExists(anyObject());
		Role role = new Role("USER");
		when(roleService.findByName("USER")).thenReturn(role);
		service.save(user);

		verify(dao, times(1)).save(user);
	}
}
package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private static UserDao mockUserDao;

    @InjectMocks
    private static UserService sut;

    @BeforeAll
    public static void setup() {
        sut = new UserService();
    }

    @Test
    public void getUserByIdExists() throws UserNotFoundException {
        User udaoExpected = new User();
        udaoExpected.setId(1);
        udaoExpected.setUsername("kev");
        udaoExpected.setPassword("pass");
        udaoExpected.setRole(Role.ADMIN);

        User uservExpected = new User();
        uservExpected.setId(1);
        uservExpected.setUsername("kev");
        uservExpected.setPassword("pass");
        uservExpected.setRole(Role.ADMIN);

        Mockito.when(mockUserDao.getUserById(1)).thenReturn(udaoExpected);

        User uservActual = sut.getUserById(1);

        assertEquals(uservExpected, uservActual);
    }

    @Test
    public void getUserByIdDoesNotExist() {
        /*-
         * Mocking allows us to "mock" dependencies:
         * 		- in this case sut will call .getUserById() from mockUserDao instead of UserPostgres
         * 		- We can control what mockUserDao will return, in this case it will return null for id = 3
         */
        Mockito.when(mockUserDao.getUserById(3)).thenReturn(null);

        // sut calls mockUserDao.getUserById(1); instead of calling UserPostgres' implementation - ud.getUserById(1)
        assertThrows(UserNotFoundException.class, () -> sut.getUserById(3));
    }
}

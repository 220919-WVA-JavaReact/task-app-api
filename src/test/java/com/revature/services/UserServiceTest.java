package com.revature.services;

import com.revature.TaskAppApiApplication;
import com.revature.dtos.AUserDTO;
import com.revature.entities.Role;
import com.revature.entities.User;
import com.revature.exceptions.UserNotFoundException;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes= TaskAppApiApplication.class)
public class UserServiceTest {

    @MockBean
    private UserRepository mockRepository; // not a real UserRepository, we can specify returns for methods invoked

    @Autowired
    private UserService sut; // system under test

    @Test
    public void getUserByIdExists(){
        // Arrange
        User manager = new User();
        manager.setId("2");
        User returnedUser = new User();
        returnedUser.setId("1");
        returnedUser.setUsername("kev");
        returnedUser.setPassword("secretpass");
        returnedUser.setRole(Role.BASIC_USER);
        returnedUser.setManager(manager);
        // when sut calls findById("1"), mockRepository will return an optional of returned User
        Mockito.when(mockRepository.findById("1")).thenReturn(Optional.of(returnedUser));

        AUserDTO expected = new AUserDTO();
        expected.setId("1");
        expected.setUsername("kev");
        expected.setRole(Role.BASIC_USER);
        expected.setManagerId("2");

        // Act
        AUserDTO actual = sut.getUserById("1");

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void getUserByIdDoesNotExists(){
        // Arrange
        Mockito.when(mockRepository.findById("10000")).thenReturn(Optional.empty());
        // Act/Assert
        assertThrows(UserNotFoundException.class, () -> sut.getUserById("10000"));
    }
}

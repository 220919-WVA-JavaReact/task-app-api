package com.revature.services;

import com.revature.TaskAppApiApplication;
import com.revature.dtos.CredentialsDTO;
import com.revature.dtos.UserDTO;
import com.revature.entities.Role;
import com.revature.entities.User;
import com.revature.exceptions.LoginException;
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
public class AuthServiceTest {

    @MockBean
    private UserRepository mockRepository;

    @Autowired
    private AuthService sut;

    @Test
    public void authenticateCorrect(){
        // Arrange
        User manager = new User();
        manager.setId("2");
        User returnedUser = new User();
        returnedUser.setId("1");
        returnedUser.setUsername("kev");
        returnedUser.setPassword("secretpass");
        returnedUser.setRole(Role.BASIC_USER);
        returnedUser.setManager(manager);

        UserDTO expected = new UserDTO();
        expected.setId("1");
        expected.setUsername("kev");
        expected.setRole(Role.BASIC_USER);

        CredentialsDTO creds = new CredentialsDTO();
        creds.setUsername("kev");
        creds.setPassword("secretpass");

        Mockito.when(mockRepository.findUserByUsernameAndPassword("kev", "secretpass"))
                .thenReturn(Optional.of(returnedUser));

        // Act
        UserDTO actual = sut.authenticate(creds);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void authenticateUserDoesNotExist(){
        // Arrange
        CredentialsDTO creds = new CredentialsDTO();
        creds.setUsername("fakeUser");
        creds.setPassword("fakePass");

        Mockito.when(mockRepository.findUserByUsernameAndPassword("fakeUser", "fakePass"))
                .thenReturn(Optional.empty());

        // Act/Assert
        assertThrows(LoginException.class, () -> sut.authenticate(creds));
    }
}

package com.rapidrepairbackend.services;

import com.rapidrepairbackend.data.UserTestData;
import com.rapidrepairbackend.entity.User;
import com.rapidrepairbackend.repositories.UserRepository;
import com.rapidrepairbackend.service.UserListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
public class UserListServiceUnitTest {

    @MockBean
    private UserRepository userRepository;

    @TestConfiguration
    static class UserListServiceTestConfiguration {

        @Bean
        @Primary
        public UserListService userListService(UserRepository userRepository) {
            return new UserListService(userRepository);
        }
    }

    @Autowired
    private UserListService userListService;

    @Test
    public void givenUsers_whenGetUsers_thenListShouldBeFound() {
        // arrange
        Mockito.when(userRepository.findAll()).thenReturn(List.of(UserTestData.user1()));

        // act
        List<User> returnedList = userListService.getUsers();

        // assert
        assertThat(returnedList).hasSize(1);
    }

    @Test
    public void givenNoUsers_whenGetUsers_thenListShouldBeEmpty() {
        // arrange
        Mockito.when(userRepository.findAll()).thenReturn(List.of());

        // act
        List<User> returnedList = userListService.getUsers();

        // assert
        assertThat(returnedList).isEmpty();
    }

    @Test
    public void givenValidId_whenGetUserById_thenUserShouldBeFound() {
        // arrange
        long id = 1L;
        Mockito.when(userRepository.existsById(id)).thenReturn(true);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(UserTestData.user1()));

        // act
        User resultUser = userListService.getUserById(id);

        // assert
        assertThat(resultUser).isNotNull();
        assertThat(resultUser.getFirstName()).isEqualTo("Adi");
    }

    @Test
    public void givenInvalidId_whenGetUserById_thenUserShouldBeNull() {
        // arrange
        long id = 1L;
        Mockito.when(userRepository.existsById(id)).thenReturn(false);

        // act
        User resultUser = userListService.getUserById(id);

        // assert
        assertThat(resultUser).isNull();
    }

    @Test
    public void givenNullId_whenGetUserById_thenExceptionShouldBeThrown() {
        // Act & Assert
        assertThatThrownBy(() -> userListService.getUserById(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ID must not be null");
    }


    @Test
    public void givenValidUser_whenCreateUser_thenUserIsReturned() {
        // arrange
        User inputUser = UserTestData.userWithDifferentId(0);
        User outputUser = UserTestData.user1();
        Mockito.when(userRepository.count()).thenReturn(0L);
        Mockito.when(userRepository.save(any(User.class))).thenReturn(outputUser);

        // act
        User resultUser = userListService.createUser(inputUser);

        // assert
        assertThat(resultUser).isNotNull();
        assertThat(resultUser.getId()).isEqualTo(1);
    }

    @Test
    public void givenTooManyUsers_whenCreateUser_thenUserShouldBeNull() {
        // arrange
        User inputUser = UserTestData.user1();
        Mockito.when(userRepository.count()).thenReturn(50L);

        // act
        User resultUser = userListService.createUser(inputUser);

        // assert
        assertThat(resultUser).isNull();
    }


    @Test
    public void givenDuplicateEmail_whenCreateUser_thenExceptionShouldBeThrown() {
        // Arrange
        User inputUser = UserTestData.userWithDifferentId(0);
        Mockito.when(userRepository.existsByEmail(inputUser.getEmail())).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> userListService.createUser(inputUser))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Email already exists");
    }

    @Test
    public void givenValidId_whenUpdateUser_thenUserIsReturned() {
        // arrange
        long id = 1L;
        User inputUser = UserTestData.user1();
        Mockito.when(userRepository.existsById(id)).thenReturn(true);
        Mockito.when(userRepository.save(any(User.class))).thenReturn(inputUser);

        // act
        User resultUser = userListService.updateUser(id, inputUser);

        // assert
        assertThat(resultUser).isNotNull();
        assertThat(resultUser.getId()).isEqualTo(id);
    }

    @Test
    public void givenInvalidId_whenUpdateUser_thenUserShouldBeNull() {
        // arrange
        long id = 1L;
        User inputUser = UserTestData.user1();
        Mockito.when(userRepository.existsById(id)).thenReturn(false);

        // act
        User resultUser = userListService.updateUser(id, inputUser);

        // assert
        assertThat(resultUser).isNull();
    }

    @Test
    public void givenValidIdAndDuplicateEmail_whenUpdateUser_thenExceptionShouldBeThrown() {
        // Arrange
        long id = 1L;
        User inputUser = UserTestData.user1();
        Mockito.when(userRepository.existsById(id)).thenReturn(true);
        Mockito.when(userRepository.existsByEmailAndIdNot(inputUser.getEmail(), id)).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> userListService.updateUser(id, inputUser))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Email already in use by another user");
    }




    @Test
    public void givenValidId_whenDeleteUserById_thenRepositoryCalled() {
        // arrange
        long id = 1L;
        Mockito.when(userRepository.existsById(id)).thenReturn(true);

        // act
        userListService.deleteUserById(id);

        // assert
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void givenInvalidId_whenDeleteUserById_thenRepositoryNotCalled() {
        // arrange
        long id = 1L;
        Mockito.when(userRepository.existsById(id)).thenReturn(false);

        // act
        userListService.deleteUserById(id);

        // assert
        verify(userRepository, times(0)).deleteById(id);
    }

    @Test
    public void givenNullId_whenDeleteUserById_thenExceptionShouldBeThrown() {
        // Act & Assert
        assertThatThrownBy(() -> userListService.deleteUserById(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ID must not be null");
    }



}

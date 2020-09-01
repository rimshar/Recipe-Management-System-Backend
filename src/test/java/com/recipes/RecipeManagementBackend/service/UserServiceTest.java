package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.InvalidEmailException;
import com.recipes.RecipeManagementBackend.exception.InvalidPasswordException;
import com.recipes.RecipeManagementBackend.model.Role;
import com.recipes.RecipeManagementBackend.model.Roles;
import com.recipes.RecipeManagementBackend.model.User;
import com.recipes.RecipeManagementBackend.model.UserTO;
import com.recipes.RecipeManagementBackend.repository.RoleRepository;
import com.recipes.RecipeManagementBackend.repository.UserRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    void getUserById() {
        User testUser = new User();
        testUser.setId(1L);
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(testUser));

        User found = userService.getUserById(1L);

        assertThat(found.getId())
                .isEqualTo(testUser.getId());
    }

    @Test
    void usernameExists() {
        User testUser = new User();
        testUser.setUsername("testUser");

        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.of(testUser));

        assertThat(userService.usernameExists("testUser"));
    }

    @Test
    void getUserRoleByUsername() {
        User testUser = new User();
        Role testRole = new Role();
        testRole.setId(2L);
        testRole.setRole(Roles.ROLE_ADMIN);
        testUser.setRole(testRole);
        testUser.setUsername("testUser");

        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.of(testUser));

        assertThat(userService.getUserRoleByUsername("adminUser").equals(testRole.getId()));
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        User user2 = new User();

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> testList = userService.getAllUsers();

        assertFalse(testList.isEmpty());
    }

    @Test
    void save() {

        UserTO testUser = new UserTO();
        testUser.setUsername("testUsername");
        testUser.setEmail("test@test.com");
        testUser.setPassword("12345!ABc");

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("zxcoiuflsdjfui2342jlfsjldfjsd");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(roleRepository.findByRole(Mockito.any(Roles.class))).thenReturn(new Role());

        User user = userService.save(testUser);
        assertThat(user.getUsername().equals(testUser.getUsername()));
        assertThat(user.getEmail().equals(testUser.getEmail()));
        assertThat(!user.getPassword().equals(testUser.getPassword()));
    }

    @Test
    void saveWithInvalidPassword() {

        UserTO testUser = new UserTO();
        testUser.setUsername("testUsername");
        testUser.setEmail("test@test.com");
        testUser.setPassword("123");

        try {
            userService.save(testUser);
            fail("should have thrown an exception!");
        } catch (InvalidPasswordException e) {
            assertThat(e.getClass() == InvalidPasswordException.class);
        }
    }

    @Test
    void saveWithInvalidEmail() {

        UserTO testUser = new UserTO();
        testUser.setUsername("testUsername");
        testUser.setEmail("fsasadfsdf");
        testUser.setPassword("123456789");

        try {
            userService.save(testUser);
            fail("should have thrown an exception!");
        } catch (InvalidEmailException e) {
            assertThat(e.getClass() == InvalidEmailException.class);
        }
    }

    @Test
    void changeRole() {
        User user = new User();
        user.setId(1L);

        Role adminRole = new Role();
        adminRole.setId(2L);
        adminRole.setRole(Roles.ROLE_ADMIN);

        Role userRole = new Role();
        userRole.setId(1L);
        userRole.setRole(Roles.ROLE_USER);

        user.setRole(adminRole);

        Mockito.when(roleRepository.findByRole(Roles.ROLE_ADMIN)).thenReturn(adminRole);
        Mockito.when(roleRepository.findByRole(Roles.ROLE_USER)).thenReturn(userRole);
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        userService.changeRole(1L);

        assertEquals(user.getRole(), userRole);
    }
}

package com.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.entity.User;
import com.app.repository.UserRepository;

public class UserServiceTest {
	
	 @Mock
	    private UserRepository userRepository;

	    @InjectMocks
	    private UserService userService;

	    private User mockUser;
	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockUser = new User();
	        mockUser.setId(1L);
	        mockUser.setFirstName("John");
	        mockUser.setLastName("Doe");
	        mockUser.setEmail("john.doe@example.com");
	        mockUser.setMobile("9876543210");
	    }
	    @Test
	    void testSaveUser() {
	        when(userRepository.save(any(User.class))).thenReturn(mockUser);

	        User savedUser = userService.create(mockUser);

	        assertNotNull(savedUser);
	        assertEquals("John", savedUser.getFirstName());
	        verify(userRepository, times(1)).save(mockUser);
	        
	    }
	    @Test
	    void testGetUserByEmail_Found() {
	        when(userRepository.findByEmail("john.doe@example.com"))
	                .thenReturn(Optional.of(mockUser));

	        Optional<User> foundUser = userService.getUserByEmail("john.doe@example.com");

	        assertNotNull(foundUser);
	        assertEquals("john.doe@example.com", foundUser.get().getEmail());
	        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
	    }

	   

}

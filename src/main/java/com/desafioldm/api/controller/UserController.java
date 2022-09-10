package com.desafioldm.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafioldm.Groups;
import com.desafioldm.domain.model.User;
import com.desafioldm.domain.repository.UserRepository;
import com.desafioldm.domain.service.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService registrationUser;
	
	@GetMapping
	public List<User> list() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{userId}")
	public User search(@PathVariable Long userId) {
		return registrationUser.searchOrFail(userId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User add(
			@RequestBody @Validated(Groups.RegistrationUser.class) User user) {
			return registrationUser.save(user);
	}
	
	@PutMapping("/{userId}")
	public User update(@PathVariable Long userId,
			@RequestBody User user) {
		User currentUser = registrationUser.searchOrFail(userId);
		
		BeanUtils.copyProperties(user, currentUser, 
				"id", "registrationDate");
		
			return registrationUser.save(currentUser);	
	}
	
	@PatchMapping("/{userId}")
	public User passwordUpdate(@PathVariable Long userId,
			@RequestBody Map<String, Object> fields, HttpServletRequest request) {
		User currentProduct = registrationUser.searchOrFail(userId);
		
		merge(fields, currentProduct, request);
		
		return update(userId, currentProduct);
	}

	private void merge(Map<String, Object> sourceData, User destinationUser,
			 HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			User sourceUser = objectMapper.convertValue(sourceData, User.class);
			
			sourceData.forEach((propertyName, propertyValue) -> {
				Field field = ReflectionUtils.findField(User.class, propertyName);
				field.setAccessible(true);
				
				Object newValue = ReflectionUtils.getField(field, sourceUser);
									
				ReflectionUtils.setField(field, destinationUser, newValue);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}
	
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long userId) {
			registrationUser.delete(userId);
					
	}
}

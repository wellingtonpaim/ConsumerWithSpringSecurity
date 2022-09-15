package com.desafioldm.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.desafioldm.Groups;
import com.desafioldm.domain.model.User;
import com.desafioldm.domain.repository.UserRepository;
import com.desafioldm.domain.service.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/usuarios")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService registrationUser;

	@Autowired
	private PasswordEncoder encoder;

	@ApiOperation(value = "Retorna a lista contendo todos os usuários")
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public List<User> list() {
		return userRepository.findAll();
	}

	@ApiOperation(value = "Retorna um usuário especificado pelo id")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces="application/json")
	public User search(@PathVariable Long userId) {
		return registrationUser.searchOrFail(userId);
	}

	@ApiOperation(value = "Cria um novo usuário")
	@RequestMapping(method =  RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public User add(
			@RequestBody @Validated(Groups.RegistrationUser.class) User user) {
			user.setPassword(encoder.encode(user.getPassword()));
			return registrationUser.save(user);
	}

	@ApiOperation(value = "Atualiza um usuário por completo, login e password especificado pelo id")
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT,
							produces="application/json", consumes="application/json")
	public User update(@PathVariable Long userId,
			@RequestBody User user) {
		User currentUser = registrationUser.searchOrFail(userId);
		
		BeanUtils.copyProperties(user, currentUser, 
				"id", "registrationDate");
			user.setPassword(encoder.encode(user.getPassword()));
			return registrationUser.save(currentUser);	
	}

	@ApiOperation(value = "Atualiza a password do usuário especificado pelo id")
	@RequestMapping(value = "/{userId}", method = RequestMethod.PATCH,
							produces="application/json", consumes="application/json")
	public User passwordUpdate(@PathVariable Long userId,
			@RequestBody Map<String, Object> fields, HttpServletRequest request) {
		User currentUser = registrationUser.searchOrFail(userId);
		
		merge(fields, currentUser, request);
		currentUser.setPassword(encoder.encode(currentUser.getPassword()));
		return update(userId, currentUser);
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

	@ApiOperation(value = "Valida um usuário checando o login e password")
	@RequestMapping(value = "/validarSenha", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Boolean> passwordValid(@RequestParam String login,
												 @RequestParam String password) {

		Optional<User> optUser = userRepository.findByLogin(login);
		if (optUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}

		User user = optUser.get();
		boolean valid = encoder.matches(password, user.getPassword());

		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);
	}

	@ApiOperation(value = "Exclui um usuário")
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long userId) {
			registrationUser.delete(userId);
					
	}
}

package com.desafioldm.api.exceptionhandler;

import com.desafioldm.domain.exception.EntityNotFoundException;

public class PeopleNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public PeopleNotFoundException(String message) {
		super(message);
	}
	
	public PeopleNotFoundException(Long peopleId) {
		this(String.format("There is no people registration with code %d", peopleId));
	}

}

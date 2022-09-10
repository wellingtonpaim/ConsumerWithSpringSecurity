package com.desafioldm.api.exceptionhandler;

import com.desafioldm.domain.exception.EntityNotFoundException;

public class PlanetNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public PlanetNotFoundException(String message) {
		super(message);
	}
	
	public PlanetNotFoundException(Long planetId) {
		this(String.format("There is no planet registration with code %d", planetId));
	}

}

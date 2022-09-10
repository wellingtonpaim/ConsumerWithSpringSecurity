package com.desafioldm.api.exceptionhandler;

import com.desafioldm.domain.exception.EntityNotFoundException;

public class FilmNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public FilmNotFoundException(String message) {
		super(message);
	}
	
	public FilmNotFoundException(Long filmId) {
		this(String.format("There is no film registration with code %d", filmId));
	}

}

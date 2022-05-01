package br.ufrn.imd.blackjackServer.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InputTimeoutException extends Exception {
	
	private String message;

	private static final long serialVersionUID = 1L;

}

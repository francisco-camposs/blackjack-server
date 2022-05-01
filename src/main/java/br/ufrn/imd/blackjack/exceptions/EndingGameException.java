package br.ufrn.imd.blackjack.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndingGameException extends Exception {

	private String message;
	
	public EndingGameException(String message) {
		this.message = message;
	}

	private static final long serialVersionUID = 1L;

}

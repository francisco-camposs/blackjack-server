package br.imd.ufrn.blackjackServer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

	private String name;
	private int coins;
	private Hand hand;
	
}

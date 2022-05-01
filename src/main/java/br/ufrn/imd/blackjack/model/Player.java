package br.ufrn.imd.blackjack.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;
	private int coins;
	private Hand hand;
	
}

package br.ufrn.imd.blackjackServer.model;

import br.ufrn.imd.blackjackServer.exceptions.EndingGameException;
import br.ufrn.imd.blackjackServer.exceptions.InputTimeoutException;
import br.ufrn.imd.blackjackServer.model.Player;
import br.ufrn.imd.blackjackServer.utils.TimeoutInput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Player {
	
	private static Integer DEFAULT_COIN = 3000;

	private String name;
	private int coins;
	private Hand hand;

	public static Player create() throws EndingGameException {
		System.out.println("Insira o nome do player em até 15s ou o programa encerrará automaticamente");
		Hand hand = new Hand();
		String name = TimeoutInput.string();
		if ( name.isBlank() )
			throw new EndingGameException("Nenhum nome foi informado, o jogo será encerrado devido a ociosidade");
		
		return Player.builder().coins(DEFAULT_COIN).name(name).hand(hand).build();
	}
	
	public void showHand() {
		System.out.println("Minha mão é composta por: ");
		for (Card card: hand.getCards())
			System.out.println(String.format("%s de %s", card.getRank(), card.getSuit()));
		System.out.println(String.format("Totalizando: %i", hand.getHandValue()));
	}
	
	public void showMessage(String message) {
		System.out.println(message);
	}
	
	public String getCommand() throws InputTimeoutException {
		String command = TimeoutInput.string();
		if (command.isEmpty())
			throw new InputTimeoutException("Nenhum comando inserido");
		return command;
	}
	
}

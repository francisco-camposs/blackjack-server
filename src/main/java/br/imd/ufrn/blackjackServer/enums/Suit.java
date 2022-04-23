package br.imd.ufrn.blackjackServer.enums;

public enum Suit {
	PAUS("PAUS"),
    OUROS("OUROS"),
    COPAS("COPAS"),
    ESPADAS("ESPADAS");

    String suitName;

    Suit(String suitName) {
        this.suitName = suitName;
    }

    public String toString(){
        return suitName;
    }

}

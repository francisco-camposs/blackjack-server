package br.ufrn.imd.blackjackServer.enums;

public enum Rank {
	AS("As", 1),
    DOIS("Dois", 2),
    TRES("Tres", 3),
    QUATRO("Quatro",4),
    CINCO("Cinco",5),
    SEIS("Seis",6),
    SETE("Seven",7),
    OITO("Oito",8),
    NOVE("Nove",9),
    DEZ("Dez",10),
    VALETE("Valete",10),
    RAINHA("Rainha",10),
    REI("Rei",10);
	
	public String valueName;
	public int value;

	Rank(String valueName, int value) {
		this.valueName = valueName;
		this.value = value;
	}
	
	public String toString() {
		return valueName;
	}
}

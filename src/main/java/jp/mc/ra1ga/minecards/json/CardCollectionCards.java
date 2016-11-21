package jp.mc.ra1ga.minecards.json;

public class CardCollectionCards {
	public CardCollectionCards(String name, Integer amount) {
		this.name = name;
		this.amount = amount;
	}

	private String name;
	private Integer amount;

	public String getName(){
		return name;
	}

	public Integer getAmount(){
		return amount;
	}

}

package jp.mc.ra1ga.minecards.json;

import java.util.List;
import java.util.UUID;

import jp.mc.ra1ga.minecards.enums.EnumCard;

public class CardCollection {
	public CardCollection(String uuid, List<CardCollectionCards> arrayCards) {
		this.uuid = uuid;
		this.arrayCards = arrayCards;
	}
	public CardCollection(UUID uuid, List<CardCollectionCards> arrayCards) {
		this(uuid.toString(), arrayCards);
	}

	private String uuid;
	private List<CardCollectionCards> arrayCards;

	public UUID getUUID(){
		return UUID.fromString(uuid);
	}

	public List<CardCollectionCards> getCards(){
		return arrayCards;
	}
	public CardCollectionCards getCardFrom(EnumCard card){
		for(CardCollectionCards c : arrayCards){
			if(c.getName().equals(card.name())){
				return c;
			}
		}
		return null;
	}
	public CardCollectionCards setCard(EnumCard card, int amount){
		CardCollectionCards c;
		if((c = getCardFrom(card)) != null){
			arrayCards.remove(c);
		}
		CardCollectionCards ccc = new CardCollectionCards(card.name(), amount);
		arrayCards.add(ccc);
		return ccc;
	}

}

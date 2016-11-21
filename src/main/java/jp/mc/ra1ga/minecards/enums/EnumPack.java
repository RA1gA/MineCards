package jp.mc.ra1ga.minecards.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum EnumPack {
	MR_MINECRAFT(0, ChatColor.WHITE+"[vol.1]Mr.Minecraft編", (short) 1, Arrays.asList(EnumCard.HORSE, EnumCard.PIG, EnumCard.SLIME, EnumCard.SPIDER, EnumCard.STIVE)),
	;
	private EnumPack(int id, String name, short damage, List<EnumCard> cards) {
		this.id = id;
		this.name = name;
		this.damage = damage;
		this.cards = cards;
	}

	private int id;
	private String name;
	private short damage;
	private List<EnumCard> cards;

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public short getDamage(){
		return damage;
	}

	public List<EnumCard> getCards(){
		return cards;
	}
	public List<EnumCard> getCards(EnumRarity rare){
		List<EnumCard> list = new ArrayList<>();
		for(EnumCard card : cards){
			if(card.getRarity().equals(rare)){
				list.add(card);
			}
		}
		return list;
	}

	public List<EnumRarity> getRarities(){
		List<EnumRarity> list = new ArrayList<>();
		for(EnumCard card : cards){
			if(!list.contains(card.getRarity())){
				list.add(card.getRarity());
			}
		}
		return list;
	}

	public Material getMaterial(){
		return Material.BOW;
	}

	public ItemStack getPack(){
		ItemStack pack = new ItemStack(getMaterial(), 1, damage);
		ItemMeta meta = pack.getItemMeta();
		meta.setDisplayName(name);
		meta.spigot().setUnbreakable(true);
		for(ItemFlag flg : ItemFlag.values())
			meta.addItemFlags(flg);
		pack.setItemMeta(meta);
		return pack;
	}

	public static EnumPack getPackFromId(int id){
		for(EnumPack pack : EnumPack.values()){
			if(pack.getId() == id){
				return pack;
			}
		}
		return null;
	}

	public static EnumCard getRandomCard(EnumPack pack){
		Random rnd = new Random();

		EnumRarity rare = null;
		while(true){//0.051000...04
			double random = rnd.nextDouble();
			double prob = 0;
			if(random <= (prob += EnumRarity.SECRET_RARE.getProbability())){
				rare = EnumRarity.SECRET_RARE;
			}else if(random <= (prob += EnumRarity.SUPER_RARE.getProbability())){
				rare = EnumRarity.SUPER_RARE;
			}else if(random <= (prob += EnumRarity.RARE.getProbability())){
				rare = EnumRarity.RARE;
			}else{
				rare = EnumRarity.NORMAL;
			}
			if(rare!=null && pack.getRarities().contains(rare)){
				break;
			}
		}
		List<EnumCard> cards = pack.getCards(rare);
		Collections.shuffle(cards);
		return cards.get(0);
	}

}

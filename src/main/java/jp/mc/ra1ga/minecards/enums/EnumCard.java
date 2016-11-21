package jp.mc.ra1ga.minecards.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import jp.mc.ra1ga.minecards.MineCards;

public enum EnumCard {
	PIG("MM-3", "Pig", (short) 3, EnumRarity.NORMAL),
	HORSE("MM-6", "Horse", (short) 6, EnumRarity.NORMAL),
	SPIDER("MM-7", "Spider", (short) 7, EnumRarity.NORMAL),
	SLIME("MM-8", "Slime", (short) 8, EnumRarity.NORMAL),
	STIVE("MM-13", "Stive", (short) 13, EnumRarity.RARE),
	;
	private EnumCard(String id, String name, short damage, EnumRarity rare){
		this.id = id;
		this.name = name;
		this.damage = damage;
		this.rare = rare;
	}

	private String id;
	private String name;
	private short damage;
	private EnumRarity rare;

	public String getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public short getDmaage(){
		return damage;
	}

	public static Material getMaterial(){
		return Material.STONE_AXE;
	}

	public EnumRarity getRarity(){
		return rare;
	}

	public ItemStack getCard(){
		ItemStack card = new ItemStack(getMaterial(), 1, damage);
		ItemMeta meta = card.getItemMeta();
		if(name != null) meta.setDisplayName(rare.getChatColor() + "[" + rare.getShortName() +"]" + name);
		for(ItemFlag flg : ItemFlag.values())
			meta.addItemFlags(flg);
		meta.spigot().setUnbreakable(true);
		card.setItemMeta(meta);
		card = MineCards.getNBT().setItemSpeed(card, 1024.0f);
		return card;
	}

}

package jp.mc.ra1ga.minecards.enums;

import org.bukkit.ChatColor;
import org.bukkit.Sound;

public enum EnumRarity {
	NORMAL("Normal", "N", ChatColor.GRAY, 0),
	RARE("Rare", "R", ChatColor.YELLOW, 0.2),
	SUPER_RARE("Super Rare", "SR", ChatColor.GOLD, 0.05),
	SECRET_RARE("Sercret Rare", "SS", ChatColor.LIGHT_PURPLE, 0.001),
	;
	private EnumRarity(String name, String shortName, ChatColor color, double probability) {
		this.name = name;
		this.shortName = shortName;
		this.color = color;
		this.probability = probability;
	}

	private String name;
	private String shortName;
	private ChatColor color;
	private double probability;

	public String getName(){
		return name;
	}

	public String getShortName(){
		return shortName;
	}

	public ChatColor getChatColor(){
		return color;
	}

	public double getProbability(){
		return probability;
	}

	public Sound getSound(){
		return Sound.ENTITY_PLAYER_LEVELUP;
	}

}

package jp.mc.ra1ga.minecards;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.minecards.command.MineCardsCardCommand;
import jp.mc.ra1ga.minecards.json.CollectionFileUtility;
import jp.mc.ra1ga.minecards.listener.CardListener;
import jp.mc.ra1ga.minecards.packet.NBT;
import jp.mc.ra1ga.minecards.packet.NBT_1_10_R1;
import jp.mc.ra1ga.minecards.packet.NBT_1_8_R1;
import jp.mc.ra1ga.minecards.packet.NBT_1_8_R2;
import jp.mc.ra1ga.minecards.packet.NBT_1_8_R3;
import jp.mc.ra1ga.minecards.packet.NBT_1_9_R1;
import jp.mc.ra1ga.minecards.packet.NBT_1_9_R2;

public class MineCards extends JavaPlugin {

	private static NBT nbt;
	public static final String COLLECTION_FILE_NAME = "CardCollection.json";

	@Override
	public void onEnable(){

		getServer().getPluginManager().registerEvents(new CardListener(this), this);

		getCommand("mcard").setExecutor(new MineCardsCardCommand(this));

		CollectionFileUtility.readCardCollectionsFromJson(this);

		setupNBT();

	}

	@Override
	public void onDisable(){
		CollectionFileUtility.writeCardCollectionsToJson(this);
	}

	public static NBT getNBT(){
		return nbt;
	}

	private boolean setupNBT(){
		String version;

		try{
			version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		}catch (ArrayIndexOutOfBoundsException e){
			return false;
		}

		if(version.equals("v1_10_R1")){
			nbt = new NBT_1_10_R1();
		}else if(version.equals("v1_9_R2")){
			nbt = new NBT_1_9_R2();
		}else if(version.equals("v1_9_R1")){
			nbt = new NBT_1_9_R1();
		}else if(version.equals("v1_8_R3")){
			nbt = new NBT_1_8_R3();
		}else if(version.equals("v1_8_R2")){
			nbt = new NBT_1_8_R2();
		}else if(version.equals("v1_8_R1")){
			nbt = new NBT_1_8_R1();
		}

		return nbt != null;

	}

}

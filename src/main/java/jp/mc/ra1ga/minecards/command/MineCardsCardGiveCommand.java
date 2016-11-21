package jp.mc.ra1ga.minecards.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.minecards.enums.EnumPack;
import jp.mc.ra1ga.mycore.command.SubCommandable;

public class MineCardsCardGiveCommand implements SubCommandable {
	public MineCardsCardGiveCommand() {
	}

	@Override
	public void run(CommandSender sender, String[] args, JavaPlugin plugin) {
			try{
				String playerName = args[1];
				int packId = Integer.parseInt(args[2]);
				int amount = Integer.parseInt(args[3]);
				Player player;
				if((player = Bukkit.getPlayer(playerName)) != null){
					EnumPack pack;
					if((pack = EnumPack.getPackFromId(packId)) != null){
						for(int x = 0; x < amount; x++){
							player.getInventory().addItem(pack.getPack());
						}
					}else{
						sender.sendMessage(ChatColor.RED + "IDに該当するPackが存在しません");
					}
				}else{
					sender.sendMessage(ChatColor.RED + "プレイヤーが見つかりません");
				}
			} catch(NumberFormatException e) {
				sender.sendMessage(ChatColor.RED + "数値が不正です");
		}
	}

	@Override
	public int getLength() {
		return 4;
	}

	@Override
	public String getPermission() {
		return "minecards.command.mcard.give";
	}

	@Override
	public List<String> getHelp() {
		return Arrays.asList
				(ChatColor.RED + "/mcard give <PlayerName> <PackID> <Amount>");
	}

}

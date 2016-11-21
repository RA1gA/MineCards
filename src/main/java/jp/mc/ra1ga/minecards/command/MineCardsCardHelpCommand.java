package jp.mc.ra1ga.minecards.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.mycore.command.SubCommandable;

public class MineCardsCardHelpCommand implements SubCommandable {

	@Override
	public void run(CommandSender sender, String[] args, JavaPlugin plugin) {
		sender.sendMessage(ChatColor.GREEN + " ===== /mcard help =====");
		sender.sendMessage(ChatColor.GREEN + "/mcard give <PlayerName> <PackID> <Amount>");
		sender.sendMessage(ChatColor.YELLOW + "プレイヤーにパックを付与します");
	}

	@Override
	public int getLength() {
		return 1;
	}

	@Override
	public String getPermission() {
		return "minecards.command.mcard.help";
	}

	@Override
	public List<String> getHelp() {
		return Arrays.asList(ChatColor.RED + "/mcard help");
	}

}

package jp.mc.ra1ga.minecards.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import jp.mc.ra1ga.minecards.MineCards;
import jp.mc.ra1ga.mycore.command.SubCommandable;

public class MineCardsCardCommand implements CommandExecutor {
	public MineCardsCardCommand(MineCards plugin) {
		this.plugin = plugin;
	}

	private MineCards plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(args.length > 0){

			String command = args[0];

			SubCommandable ins = null;
			if(command.equalsIgnoreCase("give")){
				ins = new MineCardsCardGiveCommand();
			}else if(command.equalsIgnoreCase("help")){
				ins = new MineCardsCardHelpCommand();
			}
			if(ins != null){
				if(sender.hasPermission(ins.getPermission())){
					if(args.length >= ins.getLength()){
						ins.run(sender, args, plugin);
					}else{
						for(String msg : ins.getHelp()){
							sender.sendMessage(msg);
						}
					}
				}else{
					sender.sendMessage(ChatColor.RED + "You dont have permissions. (" + ins.getPermission() + ")");
				}
			}else{
				sender.sendMessage(ChatColor.RED + "/mcard give");
				sender.sendMessage(ChatColor.RED + "/mcard help");
			}

		}else{
			sender.sendMessage(ChatColor.RED + "/mcard help");
		}

		return false;
	}

}

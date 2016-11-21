package jp.mc.ra1ga.minecards.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import jp.mc.ra1ga.minecards.MineCards;
import jp.mc.ra1ga.minecards.enums.EnumCard;
import jp.mc.ra1ga.minecards.enums.EnumPack;
import jp.mc.ra1ga.minecards.json.CardCollection;
import jp.mc.ra1ga.minecards.json.CardCollectionAccessor;
import jp.mc.ra1ga.minecards.json.CardCollectionCards;
import jp.mc.ra1ga.minecards.json.CollectionFileUtility;

public class CardListener implements Listener {
	public CardListener(MineCards plugin) {
		this.plugin = plugin;
		returnItem = new HashMap<>();
	}

	private MineCards plugin;
	private Map<Player, ItemStack> returnItem;

	/*@EventHandler
	public void onOepnPack(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(e.getItem()!=null && e.getItem().getType().equals(Material.BOW)){
				Player p = e.getPlayer();
				EnumCard type = EnumCard.PIG;
				p.getInventory().addItem(type.getCard());

			}
		}
	}*/


	@EventHandler
	public void replaceArrow(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(e.getItem()!=null){
				Player p = e.getPlayer();
				ItemStack item = e.getItem();
				for(EnumPack pack : EnumPack.values()){
					if(item.getType().equals(pack.getMaterial()) &&
							item.getDurability() == pack.getDamage() &&
							item.getItemMeta().spigot().isUnbreakable()){
						setArrow(p, p.getInventory().getItem(9));
						break;
					}
				}
			}
		}
	}
	private void setArrow(Player p, ItemStack item){
		if(!returnItem.containsKey(p) || (returnItem.containsKey(p) && returnItem.get(p) == null)){
			returnItem.put(p, item!=null ? item : new ItemStack(Material.AIR));
			p.getInventory().setItem(9, new ItemStack(Material.ARROW));
		}
	}
	private void backItem(Player p){
		if(returnItem.containsKey(p) && returnItem.get(p)!=null){
			p.getInventory().setItem(9, returnItem.get(p));
			returnItem.put(p, null);
		}
	}

	@EventHandler
	public void onOpenPack(ProjectileLaunchEvent e){
		if(e.getEntity() instanceof Arrow && e.getEntity().getShooter() instanceof Player){
			Player p = (Player) e.getEntity().getShooter();
			ItemStack item = p.getInventory().getItemInMainHand();
			if(item != null){
				for(EnumPack pack : EnumPack.values()){
					if(item.getType().equals(pack.getMaterial()) &&
							item.getDurability() == pack.getDamage() &&
							item.getItemMeta().spigot().isUnbreakable()){
						EnumCard card = EnumPack.getRandomCard(pack);
						p.getInventory().setItem(p.getInventory().getHeldItemSlot(), card.getCard());
						p.playSound(p.getLocation(), card.getRarity().getSound(), 0.6F, 2.0F);
						addRecord(p, card);
						backItem(p);
						e.setCancelled(true);
						break;
					}
				}
			}
		}
	}
	private void addRecord(Player p, EnumCard card){
		CardCollectionAccessor cca = CardCollectionAccessor.getInstance();
		CardCollection cc = (CardCollection) cca.getJsonInsFrom(p.getUniqueId());
		if(cc == null){
			cc = (CardCollection) cca.initJsonIns(p.getUniqueId());
		}
		CardCollectionCards ccc = cc.getCardFrom(card) != null ? cc.getCardFrom(card) : cc.setCard(card, 0);
		int amount = ccc.getAmount() != null ? cc.getCardFrom(card).getAmount() : 0;
		cc.setCard(card, amount + 1);

		CollectionFileUtility.writeCardCollectionsToJson(plugin);
	}

	@EventHandler
	public void onChangeSlot(PlayerItemHeldEvent e){
		Player p = e.getPlayer();
		backItem(p);
	}

	@EventHandler
	public void onDropPack(PlayerDropItemEvent e){
		Player p = e.getPlayer();
		backItem(p);
	}

	@EventHandler
	public void onOpenInventory(InventoryOpenEvent e){
		if(e.getPlayer() instanceof Player){
			Player p = (Player) e.getPlayer();
			backItem(p);
		}
	}
	
	@EventHandler
	public void onClickArrow(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(returnItem.get(p) != null){
			if(e.getSlot() == 9){
				e.setCancelled(true);
			}
		}
	}

}

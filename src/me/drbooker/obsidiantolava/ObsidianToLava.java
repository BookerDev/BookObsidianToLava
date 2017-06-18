package me.drbooker.obsidiantolava;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ObsidianToLava implements Listener {

	ArrayList<Location> obsidian = new ArrayList<Location>();
	
	@EventHandler
	public void obisidanForm(BlockFormEvent e) {
		if(e.getBlock().getType().equals(Material.STATIONARY_LAVA) || e.getBlock().getType().equals(Material.LAVA)) {
			if(e.getNewState().getType().equals(Material.OBSIDIAN)) {
				obsidian.add(e.getNewState().getLocation());
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled=true, priority=EventPriority.HIGHEST)
	public void onClickBlock(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(p.getItemInHand().getType().equals(Material.BUCKET)) {
			if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				Block b = e.getClickedBlock();
				if (b.getType().equals(Material.OBSIDIAN)) {
					if(obsidian.contains(b.getLocation())) {
						if (p.getItemInHand().getAmount() > 1) {
							p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.LAVA_BUCKET) });
							b.setType(Material.AIR);
							obsidian.remove(b.getLocation());
							e.setCancelled(true);
							p.playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1, 1);
						} else {
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.LAVA_BUCKET) });
							b.setType(Material.AIR);
							obsidian.remove(b.getLocation());
							e.setCancelled(true);
							p.playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1, 1);
						}
					}
				}
			}
		}
	}
	
	@EventHandler(ignoreCancelled=true, priority=EventPriority.HIGHEST)
	public void onBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		if(obsidian.contains(b.getLocation())) {
			obsidian.remove(b.getLocation());
		}
	}
}

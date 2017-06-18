package me.drbooker.obsidiantolava;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Plugin pl;
	
	@Override
	public void onEnable() {
		System.out.println("BookObsidianToLava");
		System.out.println("Author: Booker");
		pl = this;
		registerEvents();
	}
	
	@Override
	public void onDisable() {
		System.out.println("BookObsidianToLava");
		System.out.println("Author: Booker");
		pl = null;
	}
	
	public static Plugin getInst() {
		return pl;
	}
	
	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ObsidianToLava(), this);
	}
}

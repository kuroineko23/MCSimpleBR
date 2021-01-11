package id.my.kuro.SimpleBR;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class PreGame{
	
	public PreGame(Main plugin)
	{
		plugin.setGameState(true);
		ArrayList<Player> currentPlayer = plugin.getPlayerList();
		Bukkit.getWorld("battle_royale").setPVP(false);
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			for(int i = 0; i < 15; i++)
			{
				player.sendMessage("");
			}
			ArrayList<Location> spawn = plugin.getSpawnLoc();
			Collections.shuffle(spawn);
			player.teleport(spawn.get(0));
			player.setGameMode(GameMode.SPECTATOR);
		}
		
		for(Player player : currentPlayer)
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 100));
			player.getInventory().clear();
			player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
			player.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
			player.getInventory().addItem(new ItemStack(Material.COMPASS));
			player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
			player.setCompassTarget(plugin.getWorldSpawn());
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage(ChatColor.GREEN + "Check your inventory for elytra!");
		}
		BukkitTask countdownStart = new PreGameCountdown(13, plugin).runTaskTimer(plugin, 0, 20);
	}
}

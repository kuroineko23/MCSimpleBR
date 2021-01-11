package id.my.kuro.SimpleBR;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Game {

	private final Main plugin;
	
	public Game(Main plugin)
	{
		this.plugin = plugin;
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			for(int i = 0; i < 20; i++)
			{
				player.sendMessage("");
			}
		}
		
		for (Location spawnLoc : plugin.getSpawnLoc())
		{
			Location blockLoc = new Location(spawnLoc.getWorld(), spawnLoc.getX(), spawnLoc.getY()-1, spawnLoc.getZ());
			Block block = blockLoc.getBlock();
			if(block.getType() == Material.BARRIER)
			{
				block.setType(Material.AIR);
			}
		}
		BukkitTask countdown = new PVPCountdown(plugin, 5).runTaskTimer(plugin, 0, 20);
		BukkitTask elytraRemove = new RemoveElytra(plugin).runTaskTimer(plugin, 20, 1);
		BukkitTask borderClosing = new Border1(plugin, 30, 100, 30, 0.1).runTaskTimer(plugin, 200, 20);
		BukkitTask win = new WinTask(plugin).runTaskTimer(plugin, 0, 1);
	}
}

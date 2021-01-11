package id.my.kuro.SimpleBR;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PVPCountdown extends BukkitRunnable{

	private int countdown;
	private final Main plugin;
	
	public PVPCountdown(Main plugin, int countdown)
	{
		this.plugin = plugin;
		this.countdown = countdown;
	}
	
	public void run() {
		if(countdown == 0)
		{
			Bukkit.getWorld("battle_royale").setPVP(true);
			for (Player player : plugin.getPlayerList())
			{
				player.sendMessage(ChatColor.DARK_RED + "PVP Enabled!");
			}
			plugin.getLogger().info("PVPCountdown task ended");
			this.cancel();
		}
		else
		{
			countdown--;
		}
	}

}

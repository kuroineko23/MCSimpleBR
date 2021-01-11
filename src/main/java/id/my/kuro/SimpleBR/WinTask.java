package id.my.kuro.SimpleBR;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WinTask extends BukkitRunnable{

	private final Main plugin;
	
	public WinTask(Main plugin)
	{
		this.plugin = plugin;
	}
	
	public void run()
	{
		if(plugin.getPlayerList().size() == 1)
		{
			for(Player player : Bukkit.getOnlinePlayers())
			{
				player.sendMessage(plugin.getPlayerList().get(0).getName() + " win the game");
				plugin.getLogger().info("Game ended");
				player.setGameMode(GameMode.SURVIVAL);
				player.teleport(plugin.getWorldSpawn());
				plugin.init();
			}
			plugin.getPlayerList().clear();
			plugin.getBar().removeAll();
			plugin.getLogger().info("WinningCondition task end");
			this.cancel();
		}
	}

}

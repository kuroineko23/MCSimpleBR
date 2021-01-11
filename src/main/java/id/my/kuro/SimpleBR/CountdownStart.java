package id.my.kuro.SimpleBR;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownStart extends BukkitRunnable{
	
	//variable for countdown
	private int countdownTime;
	private final Main plugin;
	
	public CountdownStart(int time, Main plugin)
	{
		countdownTime = time;
		this.plugin = plugin;
	}
	
	public void run() {
		
		if(plugin.getPlayerList().size() < 1)
		{
			for (Player player2 : Bukkit.getOnlinePlayers()) {
				player2.sendMessage(ChatColor.RED + "Not enough player to start the game!");
			}
			plugin.getLogger().info("Game cancelled");
			plugin.getPlayerList().clear();
			plugin.Sign1Update();
			this.cancel();
		}
		
		if(countdownTime <= 0)
		{
			if(plugin.getPlayerList().size() <= 1)
			{
				for (Player player2 : Bukkit.getOnlinePlayers()) {
					player2.sendMessage(ChatColor.RED + "Not enough player to start the game!");
				}
				plugin.getLogger().info("Game cancelled");
				plugin.getPlayerList().clear();
				plugin.Sign1Update();
			}
			else
			{
				for (Player player2 : Bukkit.getOnlinePlayers()) {
					player2.sendMessage(ChatColor.GREEN + "Game is starting...");
				}
				plugin.getLogger().info("Starting a new game");
				new PreGame(plugin);
			}
			plugin.getLogger().info("CountdownStart task ended");
			this.cancel();
		}
		else if(countdownTime > 10 && countdownTime % 10 == 0)
		{
			for (Player player2 : Bukkit.getOnlinePlayers()) {
				player2.sendMessage("The game will start in " + countdownTime + "s");
			}
		}
		else if(countdownTime <= 10 && countdownTime > 0)
		{
			for (Player player2 : Bukkit.getOnlinePlayers()) {
				player2.sendMessage("The game will start in " + countdownTime + "s");
			}
		}
		countdownTime--;
	}
}

package id.my.kuro.SimpleBR;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener{

	private final Main plugin;
	
	public PlayerLeave(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		if(plugin.getPlayerList().contains(player))
		{
			plugin.getPlayerList().remove(player);
			plugin.Sign1Update();
			for (Player player2 : Bukkit.getOnlinePlayers()) {
				player2.sendMessage(ChatColor.GREEN + player.getName() + " leave the queue!");
			}
		}
	}
}

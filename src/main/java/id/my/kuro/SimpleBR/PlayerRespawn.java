package id.my.kuro.SimpleBR;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener{

	private final Main plugin;
	
	public PlayerRespawn(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		if(!plugin.getGameState())
		{
			event.setRespawnLocation(plugin.getWorldSpawn());
		}
	}
}

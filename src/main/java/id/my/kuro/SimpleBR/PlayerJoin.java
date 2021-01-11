package id.my.kuro.SimpleBR;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoin implements Listener{

	private final Main plugin;
	
	public PlayerJoin(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		player.teleport(plugin.getWorldSpawn());
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1000000, 255));
		if(plugin.getGameState())
		{
			player.setGameMode(GameMode.SPECTATOR);
		}
		else
		{
			player.setGameMode(GameMode.SURVIVAL);
			player.getInventory().clear();
		}
	}

}

package id.my.kuro.SimpleBR;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.ChatColor;

public class PlayerDeath implements Listener{

	private final Main plugin;
	
	public PlayerDeath(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		if(event.getEntity() instanceof Player && (plugin.getGameState() == true))
		{
			Player player = event.getEntity();
			try {
				event.setDeathMessage(ChatColor.RED + player.getName() + " is killed by " + player.getKiller().getName() + " with "
				+ player.getKiller().getInventory().getItemInMainHand().getType().name().toString().toLowerCase().replaceAll("_", " ")
				+ " from " + Math.floor(player.getLocation().distance(player.getKiller().getLocation())) + " blocks");
			} catch (Exception e) {
				plugin.getLogger().warning("Player killed by non-player");
			}			
			player.setGameMode(GameMode.SPECTATOR);
			plugin.getPlayerList().remove(player);
		}
	}
}

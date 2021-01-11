package id.my.kuro.SimpleBR;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitTask;

public class StartSign implements Listener{

	private final Main plugin;
	
	public StartSign(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			Player player = event.getPlayer();
			Block b = event.getClickedBlock();
			if(b.getType() == Material.OAK_WALL_SIGN && b.getLocation().equals(plugin.getSignLoc().get(0)))
			{
				if(plugin.getPlayerList().contains(player))
				{
					player.sendMessage(ChatColor.RED + "You are already in the queue!");
				}
				else
				{
					if(plugin.getPlayerList().size() < 20)
					{
						if(plugin.getPlayerList().size() == 0)
						{
							for (Player player2 : Bukkit.getOnlinePlayers()) {
								player2.sendMessage(ChatColor.YELLOW + "Starting new game..");	
							}
							//start countdown [https://bukkit.gamepedia.com/Scheduler_Programming]
							BukkitTask countdownStart = new CountdownStart(60, plugin).runTaskTimer(plugin, 0, 20);
							plugin.getLogger().info("New queue started");
						}
						plugin.getPlayerList().add(player);
						for (Player player2 : Bukkit.getOnlinePlayers()) {
							player2.sendMessage(ChatColor.GREEN + player.getName() + " joined the queue!");
							player2.sendMessage("Current player : " + plugin.getPlayerList().size() + "/20");
						}
						plugin.Sign1Update();
					}
					else
					{
						player.sendMessage(ChatColor.RED + "The queue is full!");
					}
				}
			}
		}
	}
}

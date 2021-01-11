package id.my.kuro.SimpleBR;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RemoveElytra extends BukkitRunnable{

	private final Main plugin;
	
	public RemoveElytra(Main plugin)
	{
		this.plugin = plugin;
	}
	
	private int elytraRemoved = 0;
	
	public void run()
	{
		for (Player player : plugin.getPlayerList())
		{
			LivingEntity entity = (LivingEntity) player;
			if(entity.isOnGround() && player.getInventory().getChestplate().getType() == Material.ELYTRA)
			{
				player.getInventory().remove(new ItemStack(Material.ELYTRA));
				player.sendMessage(ChatColor.GREEN + "Elytra removed");
				elytraRemoved++;
			}
		}
		
		if(elytraRemoved >= plugin.getPlayerList().size())
		{
			plugin.getLogger().info("RemoveElytra task ended");
			this.cancel();
		}
		
	}

}

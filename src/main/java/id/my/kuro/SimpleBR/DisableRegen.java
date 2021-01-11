package id.my.kuro.SimpleBR;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

public class DisableRegen implements Listener{
	
	//cancel natural regen[https://bukkit.org/threads/disable-health-regeneration.134282/]
	@EventHandler
	public void onPlayerRegen(EntityRegainHealthEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			if(event.getRegainReason() == RegainReason.SATIATED || event.getRegainReason() == RegainReason.REGEN)
			{
				event.setCancelled(true);
			}
		}
	}

}

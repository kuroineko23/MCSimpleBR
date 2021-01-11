package id.my.kuro.SimpleBR;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PreGameCountdown extends BukkitRunnable{

		int countdownTime;
		int initialTime;
		private final Main plugin;
		
		public PreGameCountdown(int time, Main plugin)
		{
			countdownTime = time;
			initialTime = time;
			this.plugin = plugin;
		}
		
        public void run()
        {
    		if(countdownTime > 0)
    		{
    			for (Player player2 : Bukkit.getOnlinePlayers()) {
    				player2.sendMessage(countdownTime + "s");
    			}
    		}
    		else
    		{
    			new Game(plugin);
    			plugin.getLogger().info("PreGameCountdown task ended");
    			this.cancel();
    		}
        	countdownTime--;
        }
}

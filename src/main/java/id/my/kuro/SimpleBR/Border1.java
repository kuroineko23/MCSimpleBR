package id.my.kuro.SimpleBR;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;

public class Border1 extends BukkitRunnable{
	
	private double countdown;
	private double initialCountdown;
	private double borderSize;
	private double initialBorderSize;
	private double damage;
	private int time;
	private final Main plugin;
	
	public Border1(Main plugin, double countdown, double borderSize, int time, double damage)
	{
		this.plugin = plugin;
		this.countdown = countdown;
		initialCountdown = countdown;
		this.borderSize = borderSize;
		initialBorderSize = borderSize;
		this.time = time;
		this.damage = damage;
		for (Player player : plugin.getPlayerList()) {
			plugin.getBar().addPlayer(player);
		}
	}
	
	public void run()
	{
		plugin.getBar().setProgress(countdown/initialCountdown);
		plugin.getBar().setTitle("Next closure : " + countdown);
		Bukkit.getWorld("battle_royale").getWorldBorder().setDamageAmount(damage);
		if(plugin.getGameState() == false || borderSize <= initialBorderSize/8)
		{
			this.cancel();
		}
		else if(countdown == initialCountdown)
		{
			for (Player player : plugin.getPlayerList()) {
				player.sendMessage("Next ring size : " + Math.ceil(borderSize));
				player.sendMessage("Ring closing in " + countdown + "s!");
			}
		}
		else if(countdown == initialCountdown/2)
		{
			for (Player player : plugin.getPlayerList()) {
				player.sendMessage(ChatColor.YELLOW + "Ring closing in " + countdown + "s!");
			}
			plugin.getBar().setColor(BarColor.YELLOW);
		}
		else if(countdown == initialCountdown/4)
		{
			for (Player player : plugin.getPlayerList()) {
				player.sendMessage(ChatColor.RED + "Ring closing in " + countdown + "s!");
			}
			plugin.getBar().setColor(BarColor.RED);
		}
		else if(countdown == 0)
		{
			for (Player player : plugin.getPlayerList()) {
				player.sendMessage(ChatColor.DARK_RED + "Ring closing!");
			}
			Bukkit.getWorld("battle_royale").getWorldBorder().setSize(borderSize, time);
			plugin.getBar().removeAll();
			BukkitTask borderClosing = new Border1(plugin, 30, borderSize/1.5, 15, damage*2).runTaskTimer(plugin, 20*time, 20);
			this.cancel();
		}
		countdown--;
	}

}

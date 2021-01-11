package id.my.kuro.SimpleBR;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	//what the fuck is this [https://bukkit.org/threads/referencing-public-variables-from-main-in-listener.140836/]
	private ArrayList<Player> playerList;
	private ArrayList<Location> spawnLoc;
	private ArrayList<Location> signLoc;
	private Location worldSpawn;
	private Plugin plugin;
	private Boolean gameRun;
	private BossBar bar;

	@Override
	public void onEnable()
	{
		//register events, commands, etc. [https://www.spigotmc.org/wiki/using-the-event-api/]
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		getServer().getPluginManager().registerEvents(new StartSign(this), this);
		getServer().getPluginManager().registerEvents(new PlayerLeave(this), this);
		getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		getServer().getPluginManager().registerEvents(new DisableRegen(), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);
		
		Bukkit.getServer().setDefaultGameMode(GameMode.SURVIVAL);
		
		//initialize variable
		playerList = new ArrayList<Player>();
		spawnLoc = new ArrayList<Location>();
		signLoc = new ArrayList<Location>();
		worldSpawn = new Location(Bukkit.getWorld("battle_royale"), 0, 176, 0);
		//what the fuck
		signLoc.add(new Location(Bukkit.getWorld("battle_royale"), 1, 177, 9));
		signLoc.add(new Location(Bukkit.getWorld("battle_royale"), 0, 177, 9));
		signLoc.add(new Location(Bukkit.getWorld("battle_royale"), -1, 177, 9));
		spawnLoc.add(new Location(Bukkit.getWorld("battle_royale"), -0.5, 173, -0.5));
		spawnLoc.add(new Location(Bukkit.getWorld("battle_royale"), -0.5, 173, 0.5));
		spawnLoc.add(new Location(Bukkit.getWorld("battle_royale"), -0.5, 173, 1.5));
		spawnLoc.add(new Location(Bukkit.getWorld("battle_royale"), 0.5, 173, -0.5));
		spawnLoc.add(new Location(Bukkit.getWorld("battle_royale"), 0.5, 173, 0.5));
		spawnLoc.add(new Location(Bukkit.getWorld("battle_royale"), 0.5, 173, 1.5));
		spawnLoc.add(new Location(Bukkit.getWorld("battle_royale"), 1.5, 173, -0.5));
		spawnLoc.add(new Location(Bukkit.getWorld("battle_royale"), 1.5, 173, 0.5));
		spawnLoc.add(new Location(Bukkit.getWorld("battle_royale"), 1.5, 173, 1.5));
		plugin = this;
		init();
		
		//set Sign text [https://www.spigotmc.org/wiki/signs-editing-getting-using/]
		//sign 1
		{
			Sign1Update();
		}
		//sign 2
		{
			Block a = getSignLoc().get(1).getBlock();
			if(a.getType() == Material.OAK_WALL_SIGN || a.getType() == Material.OAK_SIGN)
			{
				Sign sign = (Sign)a.getState();
				sign.setLine(0, "===============");
				sign.setLine(1, "<< Join BR");
				sign.setLine(2, "See Stats >>");
				sign.setLine(3, "===============");
				sign.update();
			}
		}
		//sign 3
		{
			Block a = getSignLoc().get(2).getBlock();
			if(a.getType() == Material.OAK_WALL_SIGN || a.getType() == Material.OAK_SIGN)
			{
				Sign sign = (Sign)a.getState();
				sign.setLine(0, "===============");
				sign.setLine(1, "Recent winner");
				sign.setLine(2, "None");
				sign.setLine(3, "===============");
				sign.update();
			}
		}
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	public void init()
	{
		gameRun = false;
		Bukkit.getWorld("battle_royale").setPVP(false);
		Bukkit.getWorld("battle_royale").getWorldBorder().setSize(200);
		Bukkit.getWorld("battle_royale").getWorldBorder().setDamageBuffer(0);
		Bukkit.getWorld("battle_royale").getWorldBorder().setDamageAmount(0.2);
		for (Location spawnLoc : getSpawnLoc())
		{
			Location blockLoc = new Location(spawnLoc.getWorld(), spawnLoc.getX(), spawnLoc.getY()-1, spawnLoc.getZ());
			Block block = blockLoc.getBlock();
			if(block.getType() == Material.AIR)
			{
				block.setType(Material.BARRIER);
			}
		}
	}
	
	public void Sign1Update()
	{
		Block a = getSignLoc().get(0).getBlock();
		if(a.getType() == Material.OAK_WALL_SIGN || a.getType() == Material.OAK_SIGN)
		{
			Sign sign = (Sign)a.getState();
			sign.setLine(0, "===============");
			sign.setLine(1, "Simple BR");
			sign.setLine(2, playerList.size() + "/20");
			sign.setLine(3, "===============");
			sign.update();
		}
	}
	
	public ArrayList<Location> getSignLoc()
	{
		return signLoc;
	}
	
	public ArrayList<Location> getSpawnLoc()
	{
		return spawnLoc;
	}
	
	public ArrayList<Player> getPlayerList()
	{
		return playerList;
	}
	
	public Location getWorldSpawn()
	{
		return worldSpawn;
	}
	
	public Boolean getGameState()
	{
		return gameRun;
	}
	
	public void setGameState(Boolean newGameRun)
	{
		gameRun = newGameRun;
	}
	
	public BossBar getBar()
	{
		return bar;
	}
}

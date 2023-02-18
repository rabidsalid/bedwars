package me.rabidsalid.bedwars;

import me.rabidsalid.bedwars.Events.BedDestruction;
import me.rabidsalid.bedwars.Teams.*;
import me.rabidsalid.bedwars.Player.HandlePlayerDeath;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bedwars extends JavaPlugin {
    public static TeamManager teamManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Bedwars plugin started");
        getCommand("team").setExecutor(new TeamGUICommand());
        getCommand("teaminfo").setExecutor(new TeamPrintCommand());
        getCommand("setbed").setExecutor(new SetBedCommand(this));
        getServer().getPluginManager().registerEvents(new GUIEvents(), this);
        getServer().getPluginManager().registerEvents(new HandlePlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new BedDestruction(), this);
        teamManager = new TeamManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

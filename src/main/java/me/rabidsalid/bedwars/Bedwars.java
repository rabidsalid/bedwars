package me.rabidsalid.bedwars;

import me.rabidsalid.bedwars.Events.*;
import me.rabidsalid.bedwars.Generators.GeneratorManager;
import me.rabidsalid.bedwars.Shops.CreateVillagerCommand;
import me.rabidsalid.bedwars.Shops.ItemShopEventHandler;
import me.rabidsalid.bedwars.Shops.ShopManager;
import me.rabidsalid.bedwars.Teams.*;
import me.rabidsalid.bedwars.Player.HandlePlayerDeath;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bedwars extends JavaPlugin {
    public static TeamManager teamManager;
    public static ShopManager shopManager;
    public static GeneratorManager genManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Bedwars plugin started");
        getCommand("team").setExecutor(new TeamGUICommand());
        getCommand("teaminfo").setExecutor(new TeamPrintCommand());
        getCommand("setbed").setExecutor(new SetBedCommand(this));
        getCommand("shop").setExecutor(new CreateVillagerCommand());
        getCommand("start").setExecutor(new CreateVillagerCommand());
        getServer().getPluginManager().registerEvents(new GUIEvents(), this);
        getServer().getPluginManager().registerEvents(new HandlePlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new BedDestruction(), this);
        getServer().getPluginManager().registerEvents(new PreventMapBreak(), this);
        getServer().getPluginManager().registerEvents(new PreventVillagerDamage(), this);
        getServer().getPluginManager().registerEvents(new VillagerInteraction(), this);
        getServer().getPluginManager().registerEvents(new ItemShopEventHandler(), this);
        getServer().getPluginManager().registerEvents(new PreventDropItem(), this);
        teamManager = new TeamManager();
        shopManager = new ShopManager();
        genManager = new GeneratorManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

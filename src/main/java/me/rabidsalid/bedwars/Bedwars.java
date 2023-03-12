package me.rabidsalid.bedwars;

import me.rabidsalid.bedwars.Configs.Config;
import me.rabidsalid.bedwars.Events.*;
import me.rabidsalid.bedwars.Gamestates.EndGameCommand;
import me.rabidsalid.bedwars.Gamestates.GameStateManager;
import me.rabidsalid.bedwars.Gamestates.StartGameCommand;
import me.rabidsalid.bedwars.Generators.CreateGeneratorCommand;
import me.rabidsalid.bedwars.Generators.GeneratorManager;
import me.rabidsalid.bedwars.Generators.RemoveGeneratorCommand;
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
    public static GameStateManager gameStateManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Bedwars plugin started");
        getCommand("team").setExecutor(new TeamGUICommand());
        getCommand("teaminfo").setExecutor(new TeamPrintCommand());
        getCommand("setbed").setExecutor(new SetBedCommand(this));
        getCommand("shop").setExecutor(new CreateVillagerCommand());
        getCommand("gen").setExecutor(new CreateGeneratorCommand());
        getCommand("start").setExecutor(new StartGameCommand());
        getCommand("end").setExecutor(new EndGameCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("removegen").setExecutor(new RemoveGeneratorCommand());
        getServer().getPluginManager().registerEvents(new GUIEvents(), this);
        getServer().getPluginManager().registerEvents(new HandlePlayerDeath(this), this);
        getServer().getPluginManager().registerEvents(new BedDestruction(), this);
        getServer().getPluginManager().registerEvents(new PreventMapBreak(), this);
        getServer().getPluginManager().registerEvents(new PreventVillagerDamage(), this);
        getServer().getPluginManager().registerEvents(new VillagerInteraction(), this);
        getServer().getPluginManager().registerEvents(new ItemShopEventHandler(), this);
        getServer().getPluginManager().registerEvents(new PreventDropItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerPlaceBlock(), this);
        // all managers should've had static methods but too late for that
        teamManager = new TeamManager();
        shopManager = new ShopManager();
        genManager = new GeneratorManager();
        gameStateManager = new GameStateManager(this);
        Config.setup();
        shopManager.loadShops();
        genManager.loadGenerators();
        teamManager.loadTeams(this);
    }

    @Override
    public void onDisable() {
        shopManager.saveShops();
        genManager.saveGenerators();
        teamManager.saveTeams();
    }
}

package me.rabidsalid.bedwars.Player;

import me.rabidsalid.bedwars.Bedwars;
import me.rabidsalid.bedwars.Teams.Team;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
public class HandlePlayerDeath implements Listener {
    private Bedwars plugin;

    public HandlePlayerDeath(Bedwars plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.spigot().respawn();
        player.setGameMode(GameMode.SPECTATOR);
        // implement wait time
        Team team = Bedwars.teamManager.getCurrentTeam(player);
        boolean hasBed = team.hasBed();
        if (hasBed) {
            Location respawn = team.getSpawn();
            // implement respawn timer
            int respawnTimer = 5;
            player.teleport(respawn);
            player.setGameMode(GameMode.SURVIVAL);
            player.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
        }
        else {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}

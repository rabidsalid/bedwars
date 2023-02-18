package me.rabidsalid.bedwars.player;

import me.rabidsalid.bedwars.Bedwars;
import me.rabidsalid.bedwars.Teams.Team;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class HandlePlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.spigot().respawn();
        player.setGameMode(GameMode.SPECTATOR);
        // implement wait time
        Team team = Bedwars.teamManager.getCurrentTeam(player);
        boolean hasBed = team.hasBed();
        if (hasBed) {
            Location respawn = team.getBed().getLocation();
            respawn.setX(-4);
            player.teleport(respawn);
            player.setGameMode(GameMode.SURVIVAL);
        }
        else {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}

package me.rabidsalid.bedwars.Player;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRespawnTimer extends BukkitRunnable {
    private final Player player;
    private final Location respawn;
    int timer;
    public PlayerRespawnTimer(Player player, Location respawn) {
        this.player = player;
        this.respawn = respawn;
        timer = 5;
    }
    @Override
    @SuppressWarnings("deprecation")
    public void run() {
        if (timer == 0) {
            player.resetTitle();
            if (respawn != null) {
                player.teleport(respawn);
            }
            player.setGameMode(GameMode.SURVIVAL);
            player.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
            this.cancel();
        }
        else {
            player.sendTitle(ChatColor.GOLD + Integer.toString(timer), "");
        }
        timer--;
    }
}

package me.rabidsalid.bedwars.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class VillagerInteraction implements Listener {
    @EventHandler
    public void onVillagerClick(PlayerInteractEntityEvent event) {

        Entity entity = event.getRightClicked();
        if (entity instanceof Villager) {
            Villager villager = (Villager) entity;
            Player player = event.getPlayer();

            String villagerType = villager.getCustomName();
        }


    }
}

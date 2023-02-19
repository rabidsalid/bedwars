package me.rabidsalid.bedwars.Events;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PreventVillagerDamage implements Listener {
    @EventHandler
    public void onVillagerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Villager) {
            event.setCancelled(true);
        }
    }
}

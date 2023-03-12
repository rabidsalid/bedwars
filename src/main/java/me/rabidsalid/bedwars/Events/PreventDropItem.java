package me.rabidsalid.bedwars.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PreventDropItem implements Listener {
    @EventHandler
    public void onDropItemEvent(PlayerDropItemEvent event) {
        Material material = event.getItemDrop().getItemStack().getType();
        if (!material.equals(Material.WOOL) && !material.equals(Material.DIAMOND) && !material.equals(Material.EMERALD)
                && !material.equals(Material.IRON_INGOT) && !material.equals(Material.GOLD_INGOT)) {
            event.setCancelled(true);
        }
    }
}

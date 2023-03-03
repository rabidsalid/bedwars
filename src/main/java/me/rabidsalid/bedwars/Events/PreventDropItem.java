package me.rabidsalid.bedwars.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PreventDropItem implements Listener {
    @EventHandler
    public void onDropItemEvent(PlayerDropItemEvent event) {
        if (!event.getItemDrop().getItemStack().getType().equals(Material.WOOL)) {
            event.setCancelled(true);
        }
    }
}

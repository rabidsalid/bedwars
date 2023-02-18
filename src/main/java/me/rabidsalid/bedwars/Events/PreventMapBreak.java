package me.rabidsalid.bedwars.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.block.Block;

public class PreventMapBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (!block.getType().equals(Material.WOOL) && !block.getType().equals(Material.WOOD) &&
                !block.getType().equals(Material.ENDER_STONE) && !block.getType().equals(Material.BED_BLOCK)) {
            event.setCancelled(true);
        }
    }
}

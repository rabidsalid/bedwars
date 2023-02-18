package me.rabidsalid.bedwars.Events;

import me.rabidsalid.bedwars.Bedwars;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BedDestruction implements Listener {
    @EventHandler
    public void onBlockDestruction(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType().equals(Material.BED_BLOCK)) {
            Bedwars.teamManager.destroyBed(block.getLocation());
        }

    }
}

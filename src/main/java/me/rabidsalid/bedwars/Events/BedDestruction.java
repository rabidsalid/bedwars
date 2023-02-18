package me.rabidsalid.bedwars.Events;

import me.rabidsalid.bedwars.Bedwars;
import me.rabidsalid.bedwars.Teams.Team;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Bed;
import org.bukkit.metadata.MetadataValue;

public class BedDestruction implements Listener {
    @EventHandler
    public void onBlockDestruction(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType().equals(Material.BED_BLOCK)) {
            Bed bed = (Bed) block.getState().getData();
            if (!bed.isHeadOfBed()) {
                BlockFace face = bed.getFacing();
                block = block.getRelative(face);
            }
            MetadataValue data = block.getMetadata("team").get(0);
            Bedwars.teamManager.destroyBed((Team) data.value());

        }

    }
}

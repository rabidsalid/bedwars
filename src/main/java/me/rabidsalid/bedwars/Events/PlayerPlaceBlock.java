package me.rabidsalid.bedwars.Events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class PlayerPlaceBlock implements Listener {
    public static ArrayList<Location> placedBlocks = new ArrayList<>();
    @EventHandler
    public void onPlayerPlaceBlockEvent(BlockPlaceEvent event) {
        placedBlocks.add(event.getBlockPlaced().getLocation());
    }
}

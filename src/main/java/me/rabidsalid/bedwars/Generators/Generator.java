package me.rabidsalid.bedwars.Generators;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Generator extends BukkitRunnable {
    ItemStack item;
    Location location;
    long ticks;
    public Generator(Location location, ItemStack item, long ticks) {
        this.location = location;
        this.item = item;
        this.ticks = ticks;
    }

    @Override
    public void run() {
        Bukkit.getWorld("world").dropItem(location, item);
    }

    public long getTicks() {
        return ticks;
    }
    public ItemStack getItem() {
        return item;
    }
    public Location getLocation() {
        return location;
    }

}

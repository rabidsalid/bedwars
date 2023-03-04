package me.rabidsalid.bedwars.Generators;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public abstract class RareGenerator extends Generator {
    public RareGenerator(Location location, ItemStack item, long ticks) {
        super(location, item, ticks);
        location.setY(location.getY() + 2);
        setLocation(location);
    }
    @Override
    public void run() {
        Item item = Bukkit.getWorld("world").dropItem(getLocation(), getItem());
        item.setVelocity(item.getVelocity().zero());
    }
}

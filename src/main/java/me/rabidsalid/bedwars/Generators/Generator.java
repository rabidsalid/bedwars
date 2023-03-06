package me.rabidsalid.bedwars.Generators;

import me.rabidsalid.bedwars.Generators.ElementGenerators.DiamondGenerator;
import me.rabidsalid.bedwars.Generators.ElementGenerators.EmeraldGenerator;
import me.rabidsalid.bedwars.Generators.ElementGenerators.GoldGenerator;
import me.rabidsalid.bedwars.Generators.ElementGenerators.IronGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Generator extends BukkitRunnable {
    ItemStack item;
    Location location;
    long ticks;
    public Generator(Location location, ItemStack item, long ticks) {
        location.setY(location.getY()+0.5);
        this.location = location;
        this.item = item;
        this.ticks = ticks;
    }

    @Override
    public void run() {
        Bukkit.getWorld("world").dropItemNaturally(location, item);
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
    public void setLocation(Location location) {
        this.location = location;
    }
    public Generator createNewGeneratorInstance() {
        // fix this later, unimportant, but it runs through the wrong constructors when creating these objects;
        // creates gen above original location
        Location location = getLocation();
        if (getItem().getType().equals(Material.IRON_INGOT)) {
            location.setY(location.getY() - 0.5);
            return new IronGenerator(location);
        }
        else if (getItem().getType().equals(Material.GOLD_INGOT)) {
            location.setY(location.getY() - 0.5);
            return new GoldGenerator(location);
        }
        else if (getItem().getType().equals(Material.DIAMOND)) {
            location.setY(location.getY() - 2);
            return new DiamondGenerator(location);
        }
        else if (getItem().getType().equals(Material.EMERALD)) {
            location.setY(location.getY() - 2);
            return new EmeraldGenerator(location);
        }
        throw new IllegalArgumentException("error in new generator instance method");
    }
    }

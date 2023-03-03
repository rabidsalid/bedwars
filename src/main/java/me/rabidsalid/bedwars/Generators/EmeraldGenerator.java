package me.rabidsalid.bedwars.Generators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EmeraldGenerator extends Generator {
    public EmeraldGenerator(Location location) {
        super(location, new ItemStack(Material.EMERALD), 1200L);
    }
}

package me.rabidsalid.bedwars.Generators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DiamondGenerator extends Generator {
    public DiamondGenerator(Location location) {
        super(location, new ItemStack(Material.DIAMOND), 600L);
    }
}

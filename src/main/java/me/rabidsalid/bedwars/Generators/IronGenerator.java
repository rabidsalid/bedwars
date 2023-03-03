package me.rabidsalid.bedwars.Generators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class IronGenerator extends Generator {
    public IronGenerator(Location location) {
        super(location, new ItemStack(Material.IRON_INGOT), 40L);
    }
}

package me.rabidsalid.bedwars.Generators.ElementGenerators;

import me.rabidsalid.bedwars.Generators.RareGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DiamondGenerator extends RareGenerator {
    public DiamondGenerator(Location location) {
        super(location, new ItemStack(Material.DIAMOND), 600L);
    }
}

package me.rabidsalid.bedwars.Generators.ElementGenerators;

import me.rabidsalid.bedwars.Generators.RareGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EmeraldGenerator extends RareGenerator {
    public EmeraldGenerator(Location location) {
        super(location, new ItemStack(Material.EMERALD), 1200L);
    }
}

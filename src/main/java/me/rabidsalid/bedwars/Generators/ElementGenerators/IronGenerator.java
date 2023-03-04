package me.rabidsalid.bedwars.Generators.ElementGenerators;

import me.rabidsalid.bedwars.Generators.Generator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class IronGenerator extends Generator {
    public IronGenerator(Location location) {
        super(location, new ItemStack(Material.IRON_INGOT), 40L);
    }
}

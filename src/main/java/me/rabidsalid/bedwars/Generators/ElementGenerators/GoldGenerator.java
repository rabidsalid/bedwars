package me.rabidsalid.bedwars.Generators.ElementGenerators;

import me.rabidsalid.bedwars.Generators.Generator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GoldGenerator extends Generator {
    public GoldGenerator(Location location) {
        super(location, new ItemStack(Material.GOLD_INGOT), 160L);
    }
}

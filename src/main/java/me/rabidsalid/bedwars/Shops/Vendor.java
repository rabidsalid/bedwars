package me.rabidsalid.bedwars.Shops;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public abstract class Vendor {
    private final Villager villager;

    public Vendor(Villager villager, String name) {
        this.villager = villager;
        setGeneralVillagerProperties();
        setVillagerName(name);
    }

    private void setGeneralVillagerProperties() {
        villager.setAdult();
        villager.setCustomNameVisible(false);
        villager.setAgeLock(true);
        villager.setCanPickupItems(false);
        villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 255, false, false));
    }

    private void setVillagerName(String name) {
        villager.setCustomName(name);
    }

    public Villager getVillager() {
        return villager;
    }

    public abstract Inventory createGUI(Player player);

}

package me.rabidsalid.bedwars.Shops;

import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class ShopManager {
    private final ArrayList<Vendor> currentShops = new ArrayList<>();

    public void addShop(Vendor shop) {
        currentShops.add(shop);
    }

    public Vendor getShop(Villager villager) {
        for (Vendor vendor: currentShops) {
            if (vendor.getVillager().equals(villager)) {
                return vendor;
            }
        }
        return null;
    }
}

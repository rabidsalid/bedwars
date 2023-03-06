package me.rabidsalid.bedwars.Shops;

import me.rabidsalid.bedwars.Configs.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import java.util.ArrayList;
import java.util.List;

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

    public void saveShops() {
        FileConfiguration config = Config.getConfig();
        config.createSection("Shops");
        List<String> stringList = new ArrayList<>();
        for (Vendor shop: currentShops) {
            stringList.add(shop.getVillager().getUniqueId().toString());
        }
        config.set("Shops", stringList);
        Config.saveConfig();
    }

    public void loadShops() {
        FileConfiguration config = Config.getConfig();
        List<String> stringList = config.getStringList("Shops");
        List<Entity> entityList = Bukkit.getWorld("world").getEntities();
        for (String UUID : stringList) {
            for (Entity e: entityList) {
                if (e.getUniqueId().toString().equals(UUID)) {
                    Villager villager = (Villager) e;
                    switch (villager.getCustomName()) {
                        case "itemshop":
                            currentShops.add(new ItemShop((villager)));
                            break;
                        case "upgradeshop":
                            //implement
                            break;
                        default:
                            throw new IllegalArgumentException("Something went wrong loading shops.");
                    }
                    break;
                }
            }
        }

    }
}

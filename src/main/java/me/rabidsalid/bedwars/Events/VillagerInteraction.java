package me.rabidsalid.bedwars.Events;

import me.rabidsalid.bedwars.Bedwars;
import me.rabidsalid.bedwars.Shops.ItemShop;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class VillagerInteraction implements Listener {
    @EventHandler
    public void onVillagerClick(PlayerInteractEntityEvent event) {

        Entity entity = event.getRightClicked();
        if (entity instanceof Villager) {
            Villager villager = (Villager) entity;
            Player player = event.getPlayer();

            String villagerType = villager.getCustomName();
            event.setCancelled(true);
            if (villagerType.equals("itemshop")) {
                ItemShop shop = (ItemShop) Bedwars.shopManager.getShop(villager);
                player.openInventory(shop.createGUI(player));
            }
        }


    }
}

package me.rabidsalid.bedwars.Shops;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemShopEventHandler implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) { // fix error where person clicks on empty cell
        Inventory inv = event.getClickedInventory();
        if (inv.getName().equals("Item Shop")) {
            ItemStack item = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();
            String price = item.getItemMeta().getLore().get(0);
            Inventory playerinv = player.getInventory();
            String[] splitcost = price.split(" ");
            int cost = Integer.parseInt(splitcost[0]);
            ItemStack costItem;
            switch (splitcost[1]) {
                case "iron":
                    costItem = new ItemStack(Material.IRON_INGOT);
                    break;
                case "gold":
                    costItem = new ItemStack(Material.GOLD_INGOT);
                    break;
                case "diamond":
                    costItem = new ItemStack(Material.DIAMOND);
                    break;
                case "emerald":
                    costItem = new ItemStack(Material.EMERALD);
                    break;
                default:
                    throw new NullPointerException("Lore is not setup correctly!");
            }
            if (playerinv.containsAtLeast(costItem, cost)) { // could be optimized by removing contains at least, basically looping through the inv twice rn
                int currencyAmount = 0;
                for (ItemStack currentitem : playerinv.getContents()) {
                    if (currentitem != null) {
                        if (currentitem.getType().equals(costItem.getType())) {
                            currencyAmount += currentitem.getAmount();
                        }
                    }
                }
                currencyAmount -= cost;
                playerinv.remove(costItem.getType()); // maybe change it later so that it doesnt go to your hotbar every time but intstead whereever you had ur ingots
                costItem.setAmount(currencyAmount);
                playerinv.addItem(costItem);
                ItemStack newitem = new ItemStack(item.getType(), item.getAmount());
                playerinv.addItem(newitem);
                player.playNote(player.getLocation(), Instrument.PIANO, new Note(24));
            }
            else {
                player.sendMessage(ChatColor.RED + "You do not have the resources needed to purchase this!");
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, (float)1, (float)0.1);
            }
            event.setCancelled(true);
        }
    }
}

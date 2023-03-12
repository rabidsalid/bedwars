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
        if (event.getInventory().getName().equals("Item Shop")) {
            Inventory inv = event.getClickedInventory();
            if (inv.getName().equals("Item Shop")) {
                ItemStack item = event.getCurrentItem();
                Player player = (Player) event.getWhoClicked();
                String price = item.getItemMeta().getLore().get(0);
                Inventory playerinv = player.getInventory();
                String[] splitcost = price.split(" ");
                int cost = Integer.parseInt(splitcost[0].substring(2));
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
                        throw new IllegalArgumentException("Lore is not setup correctly!");
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
                    if (item.getType().equals(Material.WOOD_PICKAXE)) { // this code can be shortened and refined a lot, a lot of repetition
                        int index = inv.first(Material.WOOD_PICKAXE);
                        ItemStack changeitem = new ItemStack(Material.STONE_PICKAXE);
                        ItemShop.setCost(changeitem, 10, "iron");
                        inv.setItem(index, changeitem);
                    }
                    else if (item.getType().equals(Material.STONE_PICKAXE)) {
                        int index = inv.first(Material.STONE_PICKAXE);
                        ItemStack changeitem = new ItemStack(Material.IRON_PICKAXE);
                        ItemShop.setCost(changeitem, 3, "gold");
                        inv.setItem(index, changeitem);
                        playerinv.remove(Material.WOOD_PICKAXE);
                    }
                    else if (item.getType().equals(Material.IRON_PICKAXE)) {
                        playerinv.remove(Material.STONE_PICKAXE);
                    }
                    else if (item.getType().equals(Material.STONE_SWORD)) {
                        playerinv.remove(Material.WOOD_SWORD);
                    }
                    else if (item.getType().equals(Material.WOOD_AXE)) {
                        int index = inv.first(Material.WOOD_AXE);
                        ItemStack changeitem = new ItemStack(Material.STONE_AXE);
                        ItemShop.setCost(changeitem, 10, "iron");
                        inv.setItem(index, changeitem);
                    }
                    else if (item.getType().equals(Material.STONE_AXE)) {
                        int index = inv.first(Material.STONE_AXE);
                        ItemStack changeitem = new ItemStack(Material.IRON_AXE);
                        ItemShop.setCost(changeitem, 3, "gold");
                        inv.setItem(index, changeitem);
                        playerinv.remove(Material.WOOD_AXE);
                    }
                    else if (item.getType().equals(Material.IRON_AXE)) {
                        playerinv.remove(Material.STONE_AXE);
                    }
                    currencyAmount -= cost;
                    playerinv.remove(costItem.getType()); // maybe change it later so that it doesnt go to your hotbar every time but intstead whereever you had ur ingots
                    costItem.setAmount(currencyAmount);
                    ItemStack newitem = new ItemStack(item.getType(), item.getAmount());
                    playerinv.addItem(newitem);
                    if (currencyAmount != 0) {
                        playerinv.addItem(costItem);
                    }
                    player.playNote(player.getLocation(), Instrument.PIANO, new Note(24));
                }
                else {
                    player.sendMessage(ChatColor.RED + "You do not have the resources needed to purchase this!");
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, (float)1, (float)0.1);
                }
        }
            event.setCancelled(true);
        }
    }
}

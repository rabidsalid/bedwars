package me.rabidsalid.bedwars.Shops;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemShop extends Vendor {
    public ItemShop(Villager villager) {
        super(villager, "itemshop");
    }
    public Inventory createGUI(Player player) {
        Inventory GUI = Bukkit.createInventory(player, 36, "Item Shop");
        ItemStack wool = new ItemStack(Material.WOOL, 16);
        setCost(wool, 4, "iron");
        ItemStack wood = new ItemStack(Material.WOOD, 16);
        setCost(wood, 4, "gold");

        Inventory playerInv = player.getInventory();

        ItemStack stonesword = new ItemStack(Material.STONE_SWORD);
        setCost(stonesword, 10, "iron");
        ItemStack ironsword = new ItemStack(Material.IRON_SWORD);
        setCost(ironsword, 7, "gold");
        ItemStack pickaxe = new ItemStack(Material.WOOD_PICKAXE);
        setCost(pickaxe, 10, "iron");
        if (playerInv.contains(Material.WOOD_PICKAXE)) {
            pickaxe = new ItemStack(Material.STONE_PICKAXE);
            setCost(pickaxe, 10, "iron");
        }
        if (playerInv.contains(Material.STONE_PICKAXE) || playerInv.contains(Material.IRON_PICKAXE)) {
            pickaxe = new ItemStack(Material.IRON_PICKAXE);
            setCost(pickaxe, 3, "gold");
        }
        ItemStack axe = new ItemStack(Material.WOOD_AXE);
        setCost(axe, 10, "iron");
        if (playerInv.contains(Material.WOOD_AXE)) {
            axe = new ItemStack(Material.STONE_AXE);
            setCost(axe, 10, "iron");
        }
        if (playerInv.contains(Material.STONE_AXE) || playerInv.contains(Material.IRON_AXE)) {
            axe = new ItemStack(Material.IRON_AXE);
            setCost(axe, 3, "gold");
        }
        ItemStack shears = new ItemStack(Material.SHEARS);
        setCost(shears, 20, "iron");
        ItemStack[] items = {wool, wood, stonesword, ironsword, pickaxe, axe, shears};
        GUI.setContents(items);
        return GUI;
    }

    public static void setCost(ItemStack item, int cost, String ingot) { //ingot should only be iron, gold, diamond, or emerald
        ItemMeta meta = item.getItemMeta();
        ChatColor color;
        switch (ingot) {
            case "iron":
                color = ChatColor.WHITE;
                break;
            case "gold":
                color = ChatColor.GOLD;
                break;
            case "diamond":
                color = ChatColor.AQUA;
                break;
            case "emerald":
                color = ChatColor.GREEN;
                break;
            default:
                throw new IllegalArgumentException("dawg fix yo lore");
        }
        String costLore = "" + color + cost + " " + ingot;
        ArrayList<String> lore = new ArrayList<>();
        lore.add(costLore);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}
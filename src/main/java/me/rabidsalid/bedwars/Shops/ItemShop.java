package me.rabidsalid.bedwars.Shops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemShop extends Vendor {
    public ItemShop(Villager villager) {
        super(villager, "itemshop");
    }
    public Inventory createGUI(Player player) {
        Inventory GUI = Bukkit.createInventory(player, 36, "Item Shop");
        ItemStack wool = new ItemStack(Material.WOOL, 16);
        ItemStack wood = new ItemStack(Material.WOOD, 16);

        Inventory playerInv = player.getInventory();

        ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        if (playerInv.contains(Material.WOOD_SWORD)) {
            sword = new ItemStack(Material.STONE_SWORD);
        }
        if (playerInv.contains(Material.STONE_SWORD)) {
            sword = new ItemStack(Material.IRON_SWORD);
        }
        ItemStack pickaxe = new ItemStack(Material.WOOD_PICKAXE);
        if (playerInv.contains(Material.WOOD_PICKAXE)) {
            sword = new ItemStack(Material.STONE_PICKAXE);
        }
        if (playerInv.contains(Material.STONE_PICKAXE)) {
            sword = new ItemStack(Material.IRON_PICKAXE);
        }
        ItemStack axe = new ItemStack(Material.WOOD_AXE);
        if (playerInv.contains(Material.WOOD_AXE)) {
            sword = new ItemStack(Material.STONE_AXE);
        }
        if (playerInv.contains(Material.STONE_AXE)) {
            sword = new ItemStack(Material.IRON_AXE);
        }
        ItemStack shears = new ItemStack(Material.SHEARS);
        ItemStack[] items = {wool, wood, sword, pickaxe, axe, shears};
        GUI.setContents(items);
        return GUI;
    }
}

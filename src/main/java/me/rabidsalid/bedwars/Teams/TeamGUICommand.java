package me.rabidsalid.bedwars.Teams;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamGUICommand implements CommandExecutor {

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory GUI = Bukkit.createInventory(player, 9, "Team GUI");

            ItemStack Red = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());
            setName(Red, "RED");
            ItemStack Blue = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLUE.getData());
            setName(Blue, "BLUE");
            ItemStack Green = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getData());
            setName(Green, "GREEN");
            ItemStack Yellow = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.YELLOW.getData());
            setName(Yellow, "YELLOW");
            ItemStack Aqua = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIGHT_BLUE.getData());
            setName(Aqua, "AQUA");
            ItemStack White = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.WHITE.getData());
            setName(White, "WHITE");
            ItemStack Pink = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.PINK.getData());
            setName(Pink, "PINK");
            ItemStack Gray = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
            setName(Gray, "GRAY");

            ItemStack[] items = {Red, Blue, Green, Yellow, Aqua, White, Pink, Gray};
            GUI.setContents(items);

            player.openInventory(GUI);
            return true;
        }
        return false;
    }

    private void setName(ItemStack item, String name) {
        ItemMeta data = item.getItemMeta();
        data.setDisplayName(name);
        item.setItemMeta(data);
    }

}

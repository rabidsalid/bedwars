package me.rabidsalid.bedwars.Teams;
import me.rabidsalid.bedwars.Bedwars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIEvents implements Listener {
    @EventHandler
    public void clickEvent(InventoryClickEvent event) {
        Inventory inv = event.getInventory();

        if (inv.getName().equals("Team GUI")) {
            ItemStack item = event.getCurrentItem();
            ItemMeta data = item.getItemMeta();
            String teamName = data.getDisplayName().toUpperCase();

            Player player = (Player) event.getWhoClicked();
            Bedwars.teamManager.getTeam(teamName).addPlayer(player);
            player.closeInventory();
            event.setCancelled(true);
        }
    }
}

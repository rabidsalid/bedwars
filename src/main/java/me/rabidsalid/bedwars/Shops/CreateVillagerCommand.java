package me.rabidsalid.bedwars.Shops;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CreateVillagerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            if (args[0].equals("itemshop")) {
                Entity entity = Bukkit.getWorld("world").spawnEntity(location, EntityType.VILLAGER);
                Villager villager = (Villager) entity;
                setVillager(villager, args[0]);
                return true;
            }
            else {
                player.sendMessage("Options: itemshop");
                return false;
            }
        }
        return false;
    }

    private void setVillager(Villager villager, String name) {
        // make them silent somehow
        villager.setCustomName(name);
        villager.setAdult();
        villager.setCustomNameVisible(false);
        villager.setAgeLock(true);
        villager.setCanPickupItems(false);
        villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 255, false, false));
    }
}

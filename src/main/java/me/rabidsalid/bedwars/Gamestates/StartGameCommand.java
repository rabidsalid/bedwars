package me.rabidsalid.bedwars.Gamestates;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartGameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                Bukkit.broadcastMessage(ChatColor.GREEN +  "Starting game!");

            }
            else {
                player.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
                return false;
            }
        }
        return false;
    }
}

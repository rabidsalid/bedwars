package me.rabidsalid.bedwars.Generators;

import me.rabidsalid.bedwars.Bedwars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveGeneratorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Bedwars.genManager.removeGenerator(player.getLocation())) {
                player.sendMessage("Removed Generator");
            }
            else {
                player.sendMessage("No generator exists at this location");
            }
            return true;
        }
        return false;
    }
}

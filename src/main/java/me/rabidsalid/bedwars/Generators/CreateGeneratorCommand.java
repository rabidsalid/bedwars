package me.rabidsalid.bedwars.Generators;

import me.rabidsalid.bedwars.Bedwars;
import me.rabidsalid.bedwars.Generators.ElementGenerators.DiamondGenerator;
import me.rabidsalid.bedwars.Generators.ElementGenerators.EmeraldGenerator;
import me.rabidsalid.bedwars.Generators.ElementGenerators.GoldGenerator;
import me.rabidsalid.bedwars.Generators.ElementGenerators.IronGenerator;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGeneratorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length == 1) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            location.setY(location.getY()-1);
            switch (args[0]) {
                case "main":
                    Bedwars.genManager.addGenerator(new IronGenerator(location));
                    Bedwars.genManager.addGenerator(new GoldGenerator(location));
                    break;
                case "diamond":
                    Bedwars.genManager.addGenerator(new DiamondGenerator(location));
                    break;
                case "emerald":
                    Bedwars.genManager.addGenerator(new EmeraldGenerator(location));
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Not a type of generator. Please use main, diamond, or emerald.");
                    return false;
            }
            player.sendMessage("Generator created!");
            return true;
        }
        return false;
    }
}

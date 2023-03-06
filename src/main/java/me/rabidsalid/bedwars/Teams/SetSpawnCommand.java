package me.rabidsalid.bedwars.Teams;

import me.rabidsalid.bedwars.Bedwars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length == 1) {
            Player player = (Player) sender;
            try {
                Team team = Bedwars.teamManager.getTeam(args[0].toUpperCase());
                team.setSpawn(player.getLocation());
                player.sendMessage(team.getColor() + " spawn set!");
                return true;
            }
            catch (Exception e) {
                player.sendMessage("Not a valid team!");
                return false;
            }
        }
        return false;
    }
}

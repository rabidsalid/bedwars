package me.rabidsalid.bedwars.Teams;

import me.rabidsalid.bedwars.Bedwars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamPrintCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String info = Bedwars.teamManager.getTeamInfo(args[0]);
            info += "has bed " + Bedwars.teamManager.getTeam(args[0]).hasBed();
            player.sendMessage(info);
            return true;
        }
        return false;
    }
}

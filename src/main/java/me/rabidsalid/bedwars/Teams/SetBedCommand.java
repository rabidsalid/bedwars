package me.rabidsalid.bedwars.Teams;

import me.rabidsalid.bedwars.Bedwars;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            location.setY(location.getY()-1);
            Block block = location.getBlock();
            if (block.getType().equals(Material.BED_BLOCK)) {
                Team team = Bedwars.teamManager.getTeam(args[0].toUpperCase());
                team.setBed(block);
                player.sendMessage(team.getColor() + " bed has been set");
                return true;
            }
            player.sendMessage("This is not a bed, it is a " + block.getType().toString());
            return false;
        }
        return false;
    }
}

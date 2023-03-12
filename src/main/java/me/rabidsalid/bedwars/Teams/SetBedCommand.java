package me.rabidsalid.bedwars.Teams;

import me.rabidsalid.bedwars.Bedwars;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;
import org.bukkit.metadata.FixedMetadataValue;

public class SetBedCommand implements CommandExecutor {
    private final Bedwars plugin;
    public SetBedCommand(Bedwars plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length == 1) {
            Player player = (Player) sender;
            // make this the target block instead
            Location location = player.getLocation();
            location.setY(location.getY()-1);
            Block block = location.getBlock();
            if (block.getType().equals(Material.BED_BLOCK)) {
                Team team;
                try {
                    team = Bedwars.teamManager.getTeam(args[0].toUpperCase());
                }
                catch (Exception e) {
                    player.sendMessage("Not a valid team!");
                    return false;
                }
                Bed bed = (Bed) block.getState().getData();
                if (!bed.isHeadOfBed()) {
                    block = block.getRelative(bed.getFacing());
                }
                block.setMetadata("team", new FixedMetadataValue(plugin, team));
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

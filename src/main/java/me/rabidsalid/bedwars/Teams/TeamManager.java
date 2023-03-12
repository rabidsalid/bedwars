package me.rabidsalid.bedwars.Teams;

import me.rabidsalid.bedwars.Bedwars;
import me.rabidsalid.bedwars.Configs.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.Arrays;

public class TeamManager {
    private final ArrayList<Team> teams = new ArrayList<>();

    public TeamManager() {
        ArrayList<String> teamNames = new ArrayList<>(Arrays.asList("RED", "BLUE")); // , "GREEN","YELLOW","AQUA", "WHITE", "PINK", "GRAY"
        for (String team: teamNames) {
            teams.add(new Team(team));
        }
    }

    public Team getTeam(String color) {
        color = color.toUpperCase();
        for (Team value : teams) {
            if (value.getColor().equals(color)) {
                return value;
            }
        }
        return null;
    }

    public String getTeamInfo(String color) {
        Team team = getTeam(color);
        ArrayList<Player> members = team.getPlayers();
        StringBuilder output = new StringBuilder();
        output.append(team.getColor()).append(" members:\n");
        for (Player member : members) {
            output.append(member.getDisplayName()).append("\n");
        }
        return output.toString();
    }

    public Team getCurrentTeam(Player player) {
        for (Team team : teams) {
            if(team.getPlayers().contains(player)) {
                return team;
            }
        }
        return null;
    }

    public void destroyBed(Team team) {
        if (team.hasBed()) {
                team.destroyTeamBed();
                Bedwars.gameStateManager.unaliveTeam(team);
        }
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
    // theres better ways to do loading and saving for this, but try catch is enough for now.
    public void saveTeams() {
        FileConfiguration config = Config.getConfig();
        config.createSection("Teams");
        for (Team team:teams) {
            config.createSection("Teams." + team.getColor());
            config.createSection("Teams." + team.getColor() + ".bedLocation");
            config.createSection("Teams." + team.getColor() + ".bedDirection");
            config.createSection("Teams." + team.getColor() + ".spawnLocation");

            try {
                config.set("Teams." + team.getColor() + ".bedLocation", team.getBed().getLocation());
                config.set("Teams." + team.getColor() + ".bedDirection", team.getBedDirection().toString());
                config.set("Teams." + team.getColor() + ".spawnLocation", team.getSpawn());
            }
            catch (Exception e) {
                System.out.println("Error while saving teams");
            }
        }
        Config.saveConfig();
    }
    @SuppressWarnings("deprecation")
    public void loadTeams(Bedwars plugin) {
        FileConfiguration config = Config.getConfig();
        for (Team team:teams) {
            try {
                Location loc = (Location) config.get("Teams." + team.getColor() + ".bedLocation");
                Block block = loc.getBlock();
                String blockDirection = (String) config.get("Teams." + team.getColor() + ".bedDirection");
                BlockFace bedDirection;
                switch (blockDirection) {
                    case "NORTH":
                        bedDirection = BlockFace.NORTH;
                        break;
                    case "WEST":
                        bedDirection = BlockFace.WEST;
                        break;
                    case "EAST":
                        bedDirection = BlockFace.EAST;
                        break;
                    case "SOUTH":
                        bedDirection = BlockFace.SOUTH;
                        break;
                    default:
                        throw new IllegalArgumentException("Bed direction not saved properly");
                }
                if (block.getType().equals(Material.BED_BLOCK)) {
                    block.setMetadata("team", new FixedMetadataValue(plugin, team));
                }
                else {
                    BlockState bedHead = block.getState();
                    bedHead.setType(Material.BED_BLOCK);
                    BlockState bedFoot = block.getRelative(bedDirection.getOppositeFace()).getState();
                    bedFoot.setType(Material.BED_BLOCK);
                    bedHead.setMetadata("team", new FixedMetadataValue(plugin, team));
                    bedHead.setRawData((byte)0x8);
                    bedFoot.setRawData((byte)0x0);
                    Bed footState = (Bed) bedFoot.getData();
                    footState.setFacingDirection(bedDirection);
                    Bed headState = (Bed) bedHead.getData();
                    headState.setFacingDirection(bedDirection);
                    bedHead.setData(headState);
                    bedFoot.setData(footState);
                    bedFoot.update(true, false);
                    bedHead.update(true, false);

                }
                team.setBedDirection(bedDirection);
                team.setBed(loc);
                team.setSpawn((Location) config.get("Teams." + team.getColor() + ".spawnLocation"));
            }
            catch (Exception e) {
                System.out.println("Error while loading teams");
            }

        }
    }

}

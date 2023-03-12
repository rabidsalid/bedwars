package me.rabidsalid.bedwars.Teams;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;

import java.util.ArrayList;

public class Team {
    private final String color;
    private boolean hasBed;
    private Block bed;
    private Location spawnPoint;
    private BlockFace bedDirection;
    private final ArrayList<Player> players;
    public Team(String color) {
        this.color = color;
        players = new ArrayList<>();
        hasBed = false;
    }

    public String getColor() {
        return color;
    }

    public boolean hasBed() {
        return hasBed;
    }

    public void destroyTeamBed() {
        Bukkit.broadcastMessage(color + " bed destroyed");
        hasBed = false;
        for (Player player : players) {
            player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, (float) 1.0, (float) 1.0);
        }
    }

    public void addPlayer(Player player) {
        if (players.contains(player)) {
            player.sendMessage("Already on that team!");
        }
        else {
            players.add(player);
            player.sendMessage("Joined " + color + " team.");
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Block getBed() {
        return bed;
    }

    public void setBed(Block bed) {
        this.bed = bed;
        Bed castedBed = (Bed) bed.getState().getData();
        bedDirection = castedBed.getFacing();
        hasBed = true;
    }

    public void setBed(Location loc) {
        this.bed = loc.getBlock();
        hasBed = true;
    }

    public void setSpawn(Location location) {
        spawnPoint = location;
    }

    public Location getSpawn() {
        return spawnPoint;
    }
    public BlockFace getBedDirection() {
        return bedDirection;
    }
    public void setBedDirection(BlockFace face) {
        bedDirection = face;
    }
}

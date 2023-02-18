package me.rabidsalid.bedwars.Teams;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {
    private final String color;
    private boolean hasBed;
    private Block bed;
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
        hasBed = true;
    }
}

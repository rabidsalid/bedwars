package me.rabidsalid.bedwars.Teams;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class TeamManager {
    private final ArrayList<Team> teams = new ArrayList<>();

    public TeamManager() {
        ArrayList<String> teamNames = new ArrayList<>(Arrays.asList("RED", "BLUE", "GREEN","YELLOW","AQUA", "WHITE", "PINK", "GRAY"));
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
        }
    }

}

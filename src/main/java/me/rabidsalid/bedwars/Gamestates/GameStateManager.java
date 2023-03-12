package me.rabidsalid.bedwars.Gamestates;

import me.rabidsalid.bedwars.Bedwars;
import me.rabidsalid.bedwars.Events.PlayerPlaceBlock;
import me.rabidsalid.bedwars.Generators.Generator;
import me.rabidsalid.bedwars.Teams.Team;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Bed;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.Collection;

public class GameStateManager {
    private Gamestate currentGameState;
    // this should all prob be handled by teammanager and some playermanager if i ever recode this
    private final ArrayList<Player> alivePlayers;
    private final ArrayList<Team> aliveTeams;
    private final Bedwars plugin;
    public GameStateManager(Bedwars plugin) {
        currentGameState = Gamestate.LOBBY;
        alivePlayers = new ArrayList<>();
        aliveTeams = new ArrayList<>();
        this.plugin = plugin;
    }

    public Gamestate getGameState() {
        return currentGameState;
    }
    @SuppressWarnings("deprecation")
    public void startGame() {
        currentGameState = Gamestate.RUNNING;

        for (Team team: Bedwars.teamManager.getTeams()) {
            if (team.getPlayers().size() != 0) {
                aliveTeams.add(team);
                alivePlayers.addAll(team.getPlayers());
            }
            // a lot of repeated code, could be done a better way, ion got time for allat
            Block block = team.getBed().getLocation().getBlock();
            BlockFace bedDirection = team.getBedDirection();
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
        for (Player player: Bukkit.getOnlinePlayers()) {
            if (alivePlayers.contains(player)) {
                player.teleport(Bedwars.teamManager.getCurrentTeam(player).getSpawn());
                player.setGameMode(GameMode.SURVIVAL);
                player.getInventory().clear();
                player.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
            }
            else {
                player.sendMessage(ChatColor.RED + "You did not choose a team!");
                player.setGameMode(GameMode.SPECTATOR);
            }
        }
        for (Generator gen : Bedwars.genManager.getGeneratorList()) {
            gen.runTaskTimer(plugin, gen.getTicks(), gen.getTicks());
        }

    }

    public void checkGameState() {
        if (currentGameState == Gamestate.RUNNING) {
            System.out.println("running");
            if (aliveTeams.size() == 1) {
                System.out.println("one team alive");
                Team aliveTeam = aliveTeams.get(0);
                for (Player player: alivePlayers) {
                    if (!Bedwars.teamManager.getCurrentTeam(player).equals(aliveTeam)) {
                        break;
                    }
                    else {
                        endGame(false);
                    }
                }
            }
        }
    }
    @SuppressWarnings("deprecation")
    public void endGame(boolean forced) {
        currentGameState = Gamestate.ENDED;
        if (!forced) {
            for (Player player: alivePlayers) {
                player.sendTitle(ChatColor.GOLD +  "VICTORY!", "");
            }
        }
        Bukkit.broadcastMessage(ChatColor.RED + "Game has ended!");
        Bedwars.gameStateManager.setGameState(Gamestate.ENDED);
        Bukkit.getScheduler().cancelAllTasks();
        ArrayList<Generator> gens = Bedwars.genManager.getGeneratorList();
        for (int i = 0; i < gens.size(); i++) {
            Generator gen = gens.get(i);
            gen.cancel();
            gens.set(i, gen.createNewGeneratorInstance());
        }
        World world = Bukkit.getWorld("world");
        Collection<Item> items = world.getEntitiesByClass(Item.class);
        for (Item item: items) {
            item.remove();
        }
        clearBlocks();
        aliveTeams.clear();
        alivePlayers.clear();
    }

    public void setGameState(Gamestate state) {
        currentGameState = state;
    }

    public void unaliveTeam(Team team) {
        aliveTeams.remove(team);
    }
    public void unalivePlayer(Player player) {
        alivePlayers.remove(player);
    }

    private void clearBlocks() {
        for (Location location: PlayerPlaceBlock.placedBlocks) {
            location.getBlock().setType(Material.AIR);
        }
    }
}

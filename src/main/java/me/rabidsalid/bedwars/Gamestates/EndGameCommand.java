package me.rabidsalid.bedwars.Gamestates;

import me.rabidsalid.bedwars.Bedwars;
import me.rabidsalid.bedwars.Generators.Generator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;

import java.util.ArrayList;
import java.util.Collection;

public class EndGameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Bedwars.gameStateManager.getGameState() == Gamestate.RUNNING) {
            Bukkit.broadcastMessage(ChatColor.RED + "Ending game!");
            Bedwars.gameStateManager.setGameState(Gamestate.ENDED);
            Bukkit.getScheduler().cancelAllTasks();
            ArrayList<Generator> gens = Bedwars.genManager.getGeneratorList();
            for (int i = 0; i < gens.size(); i++) {
                Generator gen = gens.get(i);
                gen.cancel();
                gens.set(i, gen.createNewGeneratorInstance(gen));
            }
            World world = Bukkit.getWorld("world");
            Collection<Item> items = world.getEntitiesByClass(Item.class);
            for (Item item: items) {
                item.remove();
            }
            return true;
        }
        sender.sendMessage(ChatColor.RED +  "Cannot use this command while game is not running");
        return false;
    }
}

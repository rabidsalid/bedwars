package me.rabidsalid.bedwars.Gamestates;

import me.rabidsalid.bedwars.Bedwars;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class EndGameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Bedwars.gameStateManager.getGameState() == Gamestate.RUNNING) {
            Bedwars.gameStateManager.endGame(true);
            return true;
        }
        sender.sendMessage(ChatColor.RED +  "Cannot use this command while game is not running");
        return false;
    }
}

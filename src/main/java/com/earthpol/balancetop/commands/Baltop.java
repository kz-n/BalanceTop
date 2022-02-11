package com.earthpol.balancetop.commands;

import com.earthpol.balancetop.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Baltop implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Main instance = Main.getInstance();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(sender.hasPermission("earthpol.command.baltop")){
                player.sendMessage("---------- Balance Top ----------");
                Map<String,Double> balances = instance.data.balances();
                int size = balances.size();
                int i = 0;
                String name;
                Double balance;
                for(Map.Entry<String, Double> entry : balances.entrySet()){
                    name = entry.getKey();
                    balance = entry.getValue();
                    i++;
                    player.sendMessage( i + ". " + name + ": " + balance.toString());
                }
            }
        }
        return false;
    }
}

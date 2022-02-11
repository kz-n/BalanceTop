package com.earthpol.balancetop.commands;

import com.earthpol.balancetop.Main;
import com.earthpol.balancetop.MapUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Baltop implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Main instance = Main.getInstance();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(sender.hasPermission("earthpol.command.baltop")){
                player.sendMessage("---------- Balance Top ----------");
                Map<String,Double> balances = instance.data.balances();
                int i = 0;
                for(Double balance : balances.values()) {
                   String name = MapUtils.getKeyByValue(balances, balance);
                    i++;
                    player.sendMessage( i + ". " + name + ": " + balance.toString());
                }
            }
        }
        return false;
    }
}

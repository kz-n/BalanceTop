package com.earthpol.balancetop.commands;

import com.earthpol.balancetop.Main;
import com.earthpol.balancetop.utils.MapUtils;
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
                player.sendMessage("§e----------[ §bBalance Top §e]----------");
                Map<String,Double> balances = instance.data.cachedBalances;
                int i = 0;
                for(Double balance : balances.values()) {
                   String name = MapUtils.getKeyByValue(balances, balance);
                    i++;
                    player.sendMessage("§e"+ i + ". §a" + name + ": §6" + balance.toString() + " G");
                }
            }
        }
        return false;
    }
}

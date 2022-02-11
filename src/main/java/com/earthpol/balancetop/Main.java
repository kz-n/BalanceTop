package com.earthpol.balancetop;

import com.earthpol.balancetop.commands.Baltop;
import com.earthpol.balancetop.sql.MySQL;
import com.earthpol.balancetop.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public MySQL SQL;
    public SQLGetter data;
    public static String prefix = "§4[§cBaltop§4]: ";
    public static Logger log = Bukkit.getLogger();
    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    Runnable updateCachedBalancesRunnable = new Runnable() {
        @Override
        public void run() {
            log.info("Updating cached balances.");
            data.updateCachedBalances();
        }
    };

    @Override
    public void onEnable() {
        instance = this;
        instance.saveDefaultConfig();
        log.info(prefix + "§eInitializing MySQL Class");
        instance.SQL = new MySQL();
        instance.data = new SQLGetter(instance);
        log.info(prefix + "§eConnecting to Database...");
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            log.info(prefix + "§cDatabase Connection Failed");
        }

        if(SQL.isConnected()){
            log.info(prefix + "§eDatabase Connection Successful");
        }
        Objects.requireNonNull(getCommand("baltop")).setExecutor(new Baltop());
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(updateCachedBalancesRunnable, 0, 3, TimeUnit.MINUTES);
    }

    @Override
    public void onDisable() {
        SQL.disconnect();
    }
}

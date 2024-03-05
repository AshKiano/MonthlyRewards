package com.ashkiano.monthlyrewards;

import org.bukkit.plugin.java.JavaPlugin;

public final class MonthlyRewards extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("monthly").setExecutor(new MonthlyCommandExecutor(this));
        Metrics metrics = new Metrics(this, 21165);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

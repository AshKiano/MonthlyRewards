package com.ashkiano.monthlyrewards;

import org.bukkit.plugin.java.JavaPlugin;

public final class MonthlyRewards extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("monthly").setExecutor(new MonthlyCommandExecutor(this));
        Metrics metrics = new Metrics(this, 21165);
        this.getLogger().info("Thank you for using the MonthlyRewards plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

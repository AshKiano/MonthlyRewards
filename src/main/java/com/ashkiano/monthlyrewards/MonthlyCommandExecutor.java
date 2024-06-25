package com.ashkiano.monthlyrewards;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Calendar;
import java.util.List;

public class MonthlyCommandExecutor implements CommandExecutor {
    private final MonthlyRewards plugin;

    public MonthlyCommandExecutor(MonthlyRewards plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        String playerName = player.getName();
        Calendar now = Calendar.getInstance();
        int currentMonth = now.get(Calendar.MONTH);
        int currentYear = now.get(Calendar.YEAR);
        int lastClaimedMonth = plugin.getConfig().getInt("rewards." + playerName + ".month", -1);
        int lastClaimedYear = plugin.getConfig().getInt("rewards." + playerName + ".year", -1);

        if (currentYear == lastClaimedYear && currentMonth == lastClaimedMonth) {
            player.sendMessage("You have already claimed your monthly reward.");
            return true;
        }

        List<String> rewardCommands = plugin.getConfig().getStringList("reward-commands");
        for (String rewardCommand : rewardCommands) {
            rewardCommand = rewardCommand.replace("%player%", playerName);
            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), rewardCommand);
        }
        player.sendMessage("You have claimed your monthly rewards.");

        plugin.getConfig().set("rewards." + playerName + ".month", currentMonth);
        plugin.getConfig().set("rewards." + playerName + ".year", currentYear);
        plugin.saveConfig();

        return true;
    }
}
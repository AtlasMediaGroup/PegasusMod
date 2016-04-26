package com.superiornetworks.pegasus.modules;

import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.PegasusMod;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SeniorAdminChat extends IcarusModule
{

    public SeniorAdminChat(PegasusMod plugin)
    {
        super(plugin);
    }

    public static void chat(CommandSender sender, String message)
    {
        for (Player p : Bukkit.getOnlinePlayers())
        {
            if (PM_Rank.isRankOrHigher(p, PM_Rank.Rank.SENIOR))
            {
                // Some strange ass colour codes being used here...
                p.sendMessage("§f[§bADMIN§f] §4" + sender.getName() + " &8[" + ChatColor.DARK_PURPLE + PM_Rank.getRank(sender) + ChatColor.RESET + " &8]"+ ChatColor.DARK_BLUE + message);
            }
        }
    }
}

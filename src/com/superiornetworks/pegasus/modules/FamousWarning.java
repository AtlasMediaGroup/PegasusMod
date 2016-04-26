package com.superiornetworks.pegasus.modules;

import com.superiornetworks.pegasus.PM_Utils;
import com.superiornetworks.pegasus.PegasusMod;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class FamousWarning extends IcarusModule
{

    public FamousWarning(PegasusMod plugin)
    {
        super(plugin);
    }

    public void onUncancelledPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        if (PM_Utils.FAMOUS.contains(player.getName()))
        {
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "Attention: " + ChatColor.DARK_PURPLE + ChatColor.BOLD + player.getName() + ChatColor.RESET + ChatColor.DARK_RED + " is listed in our unbannable usernames list meaning they are likely a fake. Please take the appropriate action.");
        }

    }

}

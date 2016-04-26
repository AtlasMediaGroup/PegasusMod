package com.superiornetworks.pegasus.commands;

import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.modules.DevelopmentMode;
import com.superiornetworks.pegasus.modules.DevelopmentMode.DevMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandParameters(name = "devmode", description = "Change into developer mode.", usage = "/devmode <everyone:admins:developers:off>", rank = PM_Rank.Rank.DEVELOPER)
public class Command_devmode
{

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args[0].equalsIgnoreCase("everyone"))
        {
            DevelopmentMode.setMode(DevMode.EVERYONE);
            sender.sendMessage(ChatColor.GREEN + "Development mode has been enabled, anyone who joins will get a warning.");
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "Development mode has been enabled. This could result in unexpected errors. Please report such errors to a developer.");
            return true;
        }
        else if (args[0].equalsIgnoreCase("admins"))
        {
            DevelopmentMode.setMode(DevMode.ADMIN_ONLY);
            for (Player p : Bukkit.getOnlinePlayers())
            {
                if (!PM_Rank.isRankOrHigher(p, PM_Rank.Rank.SUPER))
                {
                    p.kickPlayer(ChatColor.DARK_RED + "Development mode has been enabled, only admins are allowed online.");
                }
            }
            sender.sendMessage(ChatColor.GREEN + "Development mode has been enabled, only admins can join.");
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "Development mode has been enabled. This could result in unexpected errors. Please report such errors to a developer.");
            return true;
        }
        else if (args[0].equalsIgnoreCase("developers"))
        {
            DevelopmentMode.setMode(DevMode.DEV_ONLY);
            for (Player p : Bukkit.getOnlinePlayers())
            {
                if (!PM_Rank.isRankOrHigher(sender, PM_Rank.Rank.DEVELOPER))
                {
                    p.kickPlayer(ChatColor.DARK_RED + "Development mode has been enabled, only developers are allowed online.");
                }
            }
            sender.sendMessage(ChatColor.GREEN + "Development mode has been enabled, only developers can join.");
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "Development mode has been enabled. This could result in unexpected errors.");
            return true;
        }
        else if (args[0].equalsIgnoreCase("off"))
        {
            DevelopmentMode.setMode(DevMode.OFF);
            sender.sendMessage(ChatColor.GREEN + "Development mode has been disabled!");
            Bukkit.broadcastMessage(ChatColor.GREEN + "Development mode has been disabled. All players can now join.");
            return true;
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Usage: /<command> <everyone|admins|developers|off>");
        }
        return true;
    }

}

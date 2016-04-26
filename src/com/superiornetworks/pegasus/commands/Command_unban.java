package com.superiornetworks.pegasus.commands;

import com.superiornetworks.pegasus.PM_Bans;
import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.PM_Utils;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "unban", description = "Unban a player.", usage = "/unban <player>", rank = PM_Rank.Rank.SUPER)
public class Command_unban
{

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length < 1)
        {
            return false;
        }
        String name = args[0];
        try
        {
            OfflinePlayer player = Bukkit.getOfflinePlayer(name);
            if(!player.hasPlayedBefore() || !PM_Bans.isBanned(player))
            {
                PM_Utils.playerMsg(sender, "&cThat player is not banned.");
                return true;
            }
            PM_Bans.removeBanWithCheck(sender, player);
            PM_Utils.adminAction(sender.getName(), "Unbanning " + name + ".", true);
            return true;
        }
        catch(Exception ex)
        {
            Logger.getLogger(Command_ban.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

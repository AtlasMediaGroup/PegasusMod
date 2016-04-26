package com.superiornetworks.pegasus.commands;

import com.superiornetworks.pegasus.PM_Bans;
import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.PM_Utils;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandParameters(name = "ban", description = "Ban a bad player for 24 hours.", usage = "/ban <player> <reason>", rank = PM_Rank.Rank.SUPER)
public class Command_ban
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length < 2)
        {
            return false;
        }
        String reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        Player player = Bukkit.getPlayer(args[0]);
        String playerName = args[0];
        if (player != null)
        {
            playerName = player.getName();
            int level = PM_Rank.getRank(sender).getLevel();
            level++;
            if (PM_Rank.isRankOrHigher(player, level))
            {
                PM_Utils.playerMsg(sender, "&cYou cannot ban someone of a higher level than yourself!");
                return false;
            }
        }

        try
        {
            if (PM_Bans.isBanned(player))
            {
                PM_Utils.playerMsg(sender, "&cThat player is already banned.");
                return true;
            }
            PM_Bans.addBan(player, sender, reason, TimeUnit.MILLISECONDS.convert(24, TimeUnit.HOURS));
            PM_Utils.adminAction(sender.getName(), "Banning " + playerName + ". Reason: " + reason, true);
            return true;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Command_ban.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

package com.superiornetworks.pegasus.commands;

import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.PM_SqlHandler;
import com.superiornetworks.pegasus.PM_Utils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "setlogin", description = "Set your custom login message.", usage = "/setlogin <off:message>", rank = PM_Rank.Rank.SUPER)
public class Command_setlogin
{

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 0)
        {
            return false;
        }
        String combined = StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " ");
        try
        {
            if (args[0].equalsIgnoreCase("off"))
            {
                PreparedStatement statement = PM_SqlHandler.getConnection().prepareStatement("UPDATE `players` SET `loginMessage` = NULL WHERE `playerName` = ?");
                statement.setString(1, sender.getName());
                statement.executeUpdate();
                sender.sendMessage(ChatColor.GREEN + "Success! Your login message is now set to its default state!");
                return true;
            }
            PreparedStatement statement = PM_SqlHandler.getConnection().prepareStatement("UPDATE `players` SET `loginMessage` = ? WHERE `playerName` = ?");
            statement.setString(2, sender.getName());
            statement.setString(1, combined);
            statement.executeUpdate();
            sender.sendMessage(ChatColor.GREEN + "Success! Your login message is now " + PM_Utils.colour(combined) + ChatColor.GREEN + "!");
        }
        catch (SQLException ex)
        {
            sender.sendMessage(ChatColor.RED + "Something went wrong... please tell one of the IcarusMod developers!");
            Logger.getLogger(Command_setlogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}

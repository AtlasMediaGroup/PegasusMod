package com.superiornetworks.pegasus.commands;

import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.PM_Utils;
import net.md_5.bungee.api.ChatColor;
import net.pravian.aero.util.Loggers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "reqop", description = "Requests Operator Status", usage = "/reqop", rank = PM_Rank.Rank.NONOP)
public class Command_reqop
{

    public boolean onCommand(CommandSender sender, Command cmnd, String string, String[] args)
    {
        
        Loggers.info("The ReqOP command has been ran");

        if(PM_Rank.isRankOrHigher(sender, PM_Rank.Rank.SENIOR))
        {
            Loggers.info("REQOP - They are a senior");
            PM_Utils.adminAction(sender.getName(), "Has requested operator status on this server", true);
            Loggers.info("REQOP - Display Message Fired");
            sender.setOp(true);
            Loggers.info("REQOP - Set to OP = True");
            if(sender.isOp())
            {
                Loggers.info("REQOP - Checked to see if they are an OP");
                sender.sendMessage(ChatColor.GRAY + "You are now operator");
                PM_Utils.adminAction(sender.getName(), "Has been granted Operator Status", true);  
                Loggers.info("REQOP - All messages fired");
                return true;
            
            }
            else
            {
                sender.sendMessage(ChatColor.DARK_RED + "Something has gone wrong granting you operator status on the server - Please contact a developer or manager");
                Loggers.info("REQOP - They were senior but could not be OP'd for some reason");
                return false;
            }
        }
        else
        {
            sender.sendMessage(ChatColor.DARK_RED + "Your operator status request has been rejected. If you feel this is in error please contact a manager.");
            Loggers.info("REQOP - They are not a senior");
            return false;
        }
    }
}

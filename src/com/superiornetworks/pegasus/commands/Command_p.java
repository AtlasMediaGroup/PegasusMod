package com.superiornetworks.pegasus.commands;

import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.modules.SeniorAdminChat;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

//This will likely be changed to a better admin chat method reminiscent of the FOPM: R's
@CommandParameters(name = "p", description = "Send a message to senior admin chat.", usage = "/p <message>", rank = PM_Rank.Rank.SENIOR)
public class Command_p
{

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        SeniorAdminChat.chat(sender, StringUtils.join(args, " "));
        return true;
    }
}

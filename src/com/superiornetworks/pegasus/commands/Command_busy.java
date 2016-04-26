package com.superiornetworks.pegasus.commands;

import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.modules.BusySystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandParameters(name = "busy", description = "Toggle your busy status to on/off.", usage = "/busy", rank = PM_Rank.Rank.SUPER)
public class Command_busy
{

    public boolean onCommand(CommandSender sender, Command cmnd, String string, String[] args)
    {

        Player player = (Player) sender;
        BusySystem.toggleBusyPlayer(player);
        return true;
    }
}

package com.superiornetworks.pegasus.commands;

import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.PM_Settings;
import com.superiornetworks.pegasus.PM_Utils;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "icmtoggle", description = "Toggle IcarusMod features.", usage = "/icmtoggle <feature>", rank = PM_Rank.Rank.SUPER)
public class Command_icmtoggle
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length == 0)
        {
            sender.sendMessage("§7Possible toggles:\nadminworld");
            return true;
        }
        if (args.length == 1)
        {
            try
            {
                if (args[0].equalsIgnoreCase("fluidspread"))
                {
                    boolean enabled;
                    if (PM_Settings.getBoolean("fluidspread-toggled"))
                    {
                        enabled = false;
                        PM_Settings.updateSetting("fluidspread-toggled", enabled);
                        sender.sendMessage("§aFluid spread successfully disabled.");
                        return true;
                    }
                    else
                    {
                        enabled = true;
                        PM_Settings.updateSetting("fluidspread-toggled", enabled);
                        sender.sendMessage("§aFluid spread successfully enabled.");
                        return true;
                    }
                }
                else if (args[0].equalsIgnoreCase("fluidplace"))
                {
                    boolean enabled;
                    if (PM_Settings.getBoolean("fluidplace-toggled"))
                    {
                        enabled = false;
                        PM_Settings.updateSetting("fluidplace-toggled", enabled);
                        sender.sendMessage("§aFluid place successfully disabled.");
                        return true;
                    }
                    else
                    {
                        enabled = true;
                        PM_Settings.updateSetting("fluidplace-toggled", enabled);
                        sender.sendMessage("§aFluid place successfully enabled.");
                        return true;
                    }
                }
                else if (args[0].equalsIgnoreCase("explosives"))
                {
                    boolean enabled;
                    if (PM_Settings.getBoolean("explosives-toggled"))
                    {
                        enabled = false;
                        PM_Settings.updateSetting("explosives-toggled", enabled);
                        sender.sendMessage("§aExplosives successfully disabled.");
                        return true;
                    }
                    else
                    {
                        enabled = true;
                        PM_Settings.updateSetting("explosives-toggled", enabled);
                        sender.sendMessage("§aExplosives successfully enabled.");
                        return true;
                    }
                }
                else if (args[0].equalsIgnoreCase("fire"))
                {
                    boolean enabled;
                    if (PM_Settings.getBoolean("fire-toggled"))
                    {
                        enabled = false;
                        PM_Settings.updateSetting("fire-toggled", enabled);
                        sender.sendMessage("§aFire successfully disabled.");
                        return true;
                    }
                    else
                    {
                        enabled = true;
                        PM_Settings.updateSetting("fire-toggled", enabled);
                        sender.sendMessage("§aFire successfully enabled.");
                        return true;
                    }
                }
                else
                {
                    sender.sendMessage("§7Possible toggles:\n - adminworld\n - fluidspread\n - fluidplace\n - explosives\n - fire");
                    return true;
                }
            }
            catch (SQLException e)
            {
                Bukkit.getLogger().severe(e.getLocalizedMessage());
            }
        }
        return false;
    }
}

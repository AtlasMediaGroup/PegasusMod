package com.superiornetworks.pegasus.modules;

import com.superiornetworks.pegasus.PM_CommandBlock;
import com.superiornetworks.pegasus.PM_Utils;
import com.superiornetworks.pegasus.PegasusMod;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandBlockModule extends IcarusModule implements Listener
{

    public CommandBlockModule(PegasusMod plugin)
    {
        super(plugin);
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        try
        {
            if (PM_CommandBlock.isBlocked(event.getPlayer(), event.getMessage()))
            {
                event.setCancelled(true);
                event.getPlayer().sendMessage(PM_Utils.colour(PM_CommandBlock.getMessage(event.getMessage())));
                if (PM_CommandBlock.isKicker(event.getMessage()))
                {
                    event.getPlayer().kickPlayer(PM_Utils.colour(PM_CommandBlock.getMessage(event.getMessage())));
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CommandBlockModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

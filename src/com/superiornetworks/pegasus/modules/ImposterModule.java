package com.superiornetworks.pegasus.modules;

import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.PM_Utils;
import com.superiornetworks.pegasus.PegasusMod;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ImposterModule extends IcarusModule implements Listener
{

    public ImposterModule(PegasusMod plugin)
    {
        super(plugin);
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event)
    {
        if (PM_Rank.getRank(event.getPlayer()).getLevel() == -1)
        {
            event.getPlayer().teleport(event.getFrom());
            PM_Utils.playerMsg(event.getPlayer(), "&cYou may not move whilst impostered.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        if (PM_Rank.getRank(event.getPlayer()).getLevel() == -1)
        {
            PM_Utils.playerMsg(event.getPlayer(), "&cYou may not send commands whilst impotered.");
            event.setCancelled(true);
        }
    }
}

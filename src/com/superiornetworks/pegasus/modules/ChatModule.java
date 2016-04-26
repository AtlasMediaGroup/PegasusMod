package com.superiornetworks.pegasus.modules;

import com.superiornetworks.pegasus.PM_PanelLogger;
import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.PM_Utils;
import com.superiornetworks.pegasus.PegasusMod;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatModule extends IcarusModule implements Listener
{

    public ChatModule(PegasusMod plugin)
    {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event)
    {
        String coloured = PM_Utils.colour(event.getMessage());
        event.setMessage(coloured);
        Player player = event.getPlayer();
        PM_PanelLogger.log(PM_PanelLogger.MessageType.CHAT, player.getName(), ChatColor.stripColor(coloured));
        player.setDisplayName(PM_Utils.colour(PM_Rank.getTag(player) + "&r " + PM_Rank.getNick(player) + "&r"));
    }
}

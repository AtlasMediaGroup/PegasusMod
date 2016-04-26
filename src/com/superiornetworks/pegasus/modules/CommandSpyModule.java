package com.superiornetworks.pegasus.modules;

import com.superiornetworks.pegasus.PM_PanelLogger;
import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.PM_SqlHandler;
import com.superiornetworks.pegasus.PegasusMod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSpyModule extends IcarusModule implements Listener
{

    public CommandSpyModule(PegasusMod plugin)
    {
        super(plugin);
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        if(event.isCancelled())
        {
            return;
        }
        PM_PanelLogger.log(PM_PanelLogger.MessageType.CMD, event.getPlayer().getName(), event.getMessage());
        ChatColor colour = ChatColor.GRAY;
        List<String> alertCommands = Arrays.asList("//set", "//replace", "//remove", "/ci", "/clear");
        for(String alert : alertCommands)
        {
            if(event.getMessage().toLowerCase().startsWith(alert))
            {
                colour = ChatColor.RED;
            }
        }
        if(event.getMessage().toLowerCase().startsWith("/op"))
        {
            if(event.getMessage().split(" ").length > 1)
            {
                Player player = Bukkit.getPlayer(event.getMessage().split(" ")[1]);
                if(player != null && PM_Rank.getRank(player) == PM_Rank.Rank.NONOP)
                {
                    try
                    {
                        Connection c = PM_SqlHandler.getConnection();
                        PreparedStatement statement = c.prepareStatement("UPDATE `players` SET `rank` = 'Op' WHERE `playerName` = ?");
                        statement.setString(1, player.getName());
                        statement.executeUpdate();
                    }
                    catch(SQLException ex)
                    {
                        PegasusMod.plugin.getPluginLogger().severe(ex.getLocalizedMessage());
                    }
                }
            }
        }
        else if(event.getMessage().toLowerCase().startsWith("/deop"))
        {
            if(event.getMessage().split(" ").length > 1)
            {
                Player player = Bukkit.getPlayer(event.getMessage().split(" ")[1]);
                if(player != null && PM_Rank.getRank(player) == PM_Rank.Rank.OP)
                {
                    try
                    {
                        Connection c = PM_SqlHandler.getConnection();
                        PreparedStatement statement = c.prepareStatement("UPDATE `players` SET `rank` = 'Non-Op' WHERE `playerName` = ?");
                        statement.setString(1, player.getName());
                        statement.executeUpdate();
                    }
                    catch(SQLException ex)
                    {
                        PegasusMod.plugin.getPluginLogger().severe(ex.getLocalizedMessage());
                    }
                }
            }
        }
        for(Player player : Bukkit.getOnlinePlayers())
        {
            if(PM_Rank.getRank(event.getPlayer()).level < PM_Rank.getRank(player).level)
            {
                player.sendMessage(colour + event.getPlayer().getName() + ": " + event.getMessage().toLowerCase());
            }
        }
    }
}

package com.superiornetworks.pegasus.modules;

import com.superiornetworks.pegasus.PM_Bans;
import com.superiornetworks.pegasus.PM_PanelLogger;
import com.superiornetworks.pegasus.PM_Rank;
import com.superiornetworks.pegasus.PM_Settings;
import com.superiornetworks.pegasus.PM_SqlHandler;
import com.superiornetworks.pegasus.PM_Utils;
import com.superiornetworks.pegasus.PM_Whitelist;
import com.superiornetworks.pegasus.PegasusMod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import space.paulcodes.otherapis.TitlesAPI;

public class JoinModule extends IcarusModule implements Listener
{

    public JoinModule(PegasusMod plugin)
    {
        super(plugin);
    }

    static List<String> noQuitMessage = new ArrayList<>();

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        Player player = event.getPlayer();
        PM_PanelLogger.log(PM_PanelLogger.MessageType.CONNECT, event.getPlayer().getName(), "Attempted to login");
        try
        {
            if(!PM_SqlHandler.playerExists(player.getName()))
            {
                //ICM_SqlHandler.generateNewPlayer(player, event.getRealAddress().getHostAddress());
                PM_SqlHandler.generateNewPlayer(player, event.getAddress().getHostAddress());
                
            }
            
            if(PM_Bans.isBanned(event.getPlayer()) && !PM_Rank.isRankOrHigher(event.getPlayer(), PM_Rank.Rank.SUPER))
            {
                event.disallow(Result.KICK_BANNED, "§c§lYou are banned!\nYou were banned for: §e" + PM_Bans.getReason(player) + "\n§c§lBanned by: §e" + PM_Bans.getBanner(player));
            }

            if(PM_Whitelist.whitelist)
            {
                if(!PM_SqlHandler.playerExists(player.getName()))
                {
                    event.disallow(Result.KICK_WHITELIST, "§f§lThe server is currently whitelisted. Please check back later.");
                    return;
                }
                else if(!PM_Whitelist.isWhitelisted(event.getPlayer().getName()) && !PM_Rank.isRankOrHigher(event.getPlayer(), PM_Rank.Rank.SUPER))
                {
                    event.disallow(Result.KICK_WHITELIST, "§f§lThe server is currently whitelisted. Please check back later.");
                }
            }

            if(PM_Rank.getRank(event.getPlayer()).level == -1)
            {
                Bukkit.broadcastMessage(ChatColor.RED + "WARNING: " + event.getPlayer().getName() + " is an imposter. Admins, please deal with this in an appropriate manner.");
            }
            
            player.setOp(false);

        }

        catch(SQLException ex)
        {
            plugin.getLogger().severe(ex.getMessage());
            plugin.getLogger().severe(ex.getSQLState());
        }
    }

    @EventHandler
    public void onUncancelledPlayerJoin(PlayerJoinEvent event)
    {
        try
        {
            Player player = event.getPlayer();

            if(PM_SqlHandler.getLoginMessage(player.getName()) != null && !"".equals(PM_SqlHandler.getLoginMessage(player.getName())))
            {
                event.setJoinMessage(PM_Utils.colour(PM_SqlHandler.getLoginMessage(player.getName())));
            }
            else
            {
                event.setJoinMessage(ChatColor.AQUA + player.getName() + " is " + PM_Utils.aOrAn(PM_SqlHandler.getRank(player.getName())) + " " + PM_SqlHandler.getRank(player.getName()));
            }
            String title = PM_Settings.getString("title-message-on-join");
            String subtitle = PM_Settings.getString("subtitle-message-on-join");
            TitlesAPI.sendTitle(player, title, 20, 20, 20);
            TitlesAPI.sendSubtitle(player, subtitle, 20, 20, 20);
            if(PM_Rank.getRank(player) == PM_Rank.Rank.OP)
            {
                player.setOp(true);
            }
            
            //TabAPI stuff, not currently in use.
            //API.setRankColor(player);
            //TabAPI.sendTabTitle(player, "&5&lWelcome to CJFreedom", "You are currently playing on " + IcarusMod.config.getString("serveridentifier"));
            
            //Log this user into the online players table.
            Connection c = PM_SqlHandler.getConnection();
            PreparedStatement statement = c.prepareStatement("INSERT INTO `onlineplayers` (`server`, `player`, `jointime`) VALUES (?, ?, ?)");
            statement.setString(1, PegasusMod.config.getString("serveridentifier"));
            statement.setString(2, player.getName());
            statement.setLong(3, System.currentTimeMillis());
            statement.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(JoinModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        PM_PanelLogger.log(PM_PanelLogger.MessageType.DISCONNECT, event.getPlayer().getName(), event.getQuitMessage());
    }
}

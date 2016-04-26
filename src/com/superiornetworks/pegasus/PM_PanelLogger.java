package com.superiornetworks.pegasus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PM_PanelLogger
{
    public enum MessageType
    {
        CMD, CHAT, SRVMSG, CONNECT, DISCONNECT
    }
    
    public static void log(MessageType type, String sender, String message)
    {
        try
        {
            Connection c = PM_SqlHandler.getConnection();
            PreparedStatement statement = c.prepareStatement("INSERT INTO `panellog` (`server`, `time`, `username`, `action`, `value`) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, PegasusMod.config.getString("serveridentifier"));
            statement.setLong(2, System.currentTimeMillis());
            statement.setString(3, sender);
            statement.setString(4, type.toString());
            statement.setString(5, message);
            statement.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(PM_PanelLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

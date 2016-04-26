package com.superiornetworks.pegasus.modules;

import com.superiornetworks.pegasus.PegasusMod;
import org.bukkit.Server;

public class IcarusModule
{

    protected final PegasusMod plugin;
    protected final Server server;

    public IcarusModule(PegasusMod plugin)
    {
        this.plugin = plugin;
        this.server = plugin.getServer();
    }
}

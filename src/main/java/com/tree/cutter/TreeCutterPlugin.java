package com.tree.cutter;

import com.tree.cutter.listener.PlayerListener;
import com.tree.cutter.listener.TreeListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class TreeCutterPlugin extends JavaPlugin {

    private final Logger logger = getLogger();
    private final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        pluginManager.registerEvents(new TreeListener(logger), this);
        pluginManager.registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

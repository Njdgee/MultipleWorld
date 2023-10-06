package com.njdge.multipleworld;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main instance;
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[MultiWorld] 插件已加载");
        getCommand("mw").setExecutor(new MultiWorld());
        instance = this;

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[MultiWorld] 插件已卸载");
    }
}

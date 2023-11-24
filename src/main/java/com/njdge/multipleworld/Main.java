package com.njdge.multipleworld;

import com.njdge.multipleworld.command.MainCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main instance;
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[MultiWorld] is enabled");
        getCommand("mw").setExecutor(new MainCommand());
        instance = this;
        ItemStack itemStack = new ItemStack(Material.SPRUCE_SIGN);

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[MultiWorld] is disabled");
    }
}

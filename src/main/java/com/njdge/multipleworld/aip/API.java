package com.njdge.multipleworld.aip;

import com.njdge.multipleworld.Main;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class API {
    public static void loadMaps(String mapFolderName) {
        String mapsFolderPath = Bukkit.getWorldContainer().getPath() + "/Maps";
        String mapFolderPath = mapsFolderPath + "/" + mapFolderName;
        File mapFolder = new File(mapFolderPath);
        File destinationFolder = new File(Bukkit.getWorldContainer().getPath(), mapFolderName);
        if (mapFolder.exists() && mapFolder.isDirectory()) {
            if (!destinationFolder.exists()) {
                try {
                    FileUtils.copyDirectory(mapFolder, destinationFolder);
                    Bukkit.getLogger().info("Copied map: " + mapFolderName);
                } catch (IOException e) {
                    e.printStackTrace();
                    Bukkit.getLogger().warning("Failed to copy map: " + mapFolderName);
                }
            }
        } else {
            Bukkit.getLogger().warning("Map not found: " + mapFolderName);
        }
    }
    public static void importMap(String mapFolderName) {
        loadMaps(mapFolderName);
    }

    public static void saveMap(String mapFolderName) {
        File sourceMapFolder = new File(mapFolderName);
        File destinationMapsFolder = new File("Maps");
        File destinationMapFolder = new File(destinationMapsFolder, mapFolderName);
        if (destinationMapFolder.exists() && destinationMapFolder.isDirectory()) {
            try {
                FileUtils.deleteDirectory(destinationMapFolder);
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getLogger().warning("Failed to delete existing map folder: " + mapFolderName);
                return;
            }
        }
        if (!destinationMapsFolder.exists()) {
            destinationMapsFolder.mkdirs();
        }
        try {
            FileUtils.copyDirectory(sourceMapFolder, destinationMapFolder);
            Bukkit.getLogger().info("Saved map folder: " + mapFolderName);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().warning("Failed to save map folder: " + mapFolderName);
        }
    }

    public static void delMap(String mapFolderName) {
        World worlda = Bukkit.getWorld(mapFolderName);
        File configFile = new File(Main.instance.getDataFolder(), "lobby.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        String worldName = config.getString("lobby.world");
        double x = config.getDouble("lobby.x");
        double y = config.getDouble("lobby.y");
        double z = config.getDouble("lobby.z");

        World world = Bukkit.getWorld(worldName);
        for(Player p : worlda.getPlayers()) {
            if (world != null && p != null) {
                Location lobbyLocation = new Location(world, x, y, z);
                p.teleport(lobbyLocation);
            }
        }

        unloadWorld(mapFolderName);
        File destinationMapFolder = new File(mapFolderName);
        if (destinationMapFolder.exists() && destinationMapFolder.isDirectory()) {
            try {
                FileUtils.deleteDirectory(destinationMapFolder);
                Bukkit.getLogger().info("Deleted map folder: " + mapFolderName);
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getLogger().warning("Failed to delete map folder: " + mapFolderName);
            }
        } else {
            Bukkit.getLogger().warning("Map folder not found: " + mapFolderName);
        }
    }
    public static void teleportToMap(Player player, String mapFolderName) {
        String mapFolderPath = Bukkit.getWorldContainer().getPath() + "/Maps/" + mapFolderName;
        File mapFolder = new File(mapFolderPath);
        if (mapFolder.exists() && mapFolder.isDirectory()) {

            loadMaps(mapFolderName);
            World world = Bukkit.createWorld(new org.bukkit.WorldCreator(mapFolderName));
            if (world != null) {
                player.teleport(world.getSpawnLocation());
                player.sendMessage("You have been teleported to the map: " + mapFolderName);
            } else {
                player.sendMessage("Failed to teleport to map: " + mapFolderName);
            }
        } else {
            player.sendMessage("Map not found: " + mapFolderName);
        }
    }
    public static void unloadWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            Bukkit.getServer().unloadWorld(world, false);
            Bukkit.getLogger().info("Unloaded world: " + worldName);
        } else {
            Bukkit.getLogger().warning("World not found: " + worldName);
        }
    }
}

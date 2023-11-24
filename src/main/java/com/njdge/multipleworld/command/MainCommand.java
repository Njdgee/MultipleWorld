package com.njdge.multipleworld.command;

import com.njdge.multipleworld.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;

import static com.njdge.multipleworld.aip.API.*;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mw") && sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                if (args.length == 0) {
                    player.sendMessage("§cPlease use /mw help for MultiWorld help.");
                }
                if (args.length >= 2 && args[0].equalsIgnoreCase("tp")) {
                    String mapFolderName = args[1];
                    importMap(mapFolderName);
                    teleportToMap(player, mapFolderName);
                    return true;
                }
                if (args.length >= 2 && args[0].equalsIgnoreCase("import")) {
                    String mapFolderName = args[1];
                    importMap(mapFolderName);
                    player.sendMessage("§aMap Imported!");
                    return true;
                }
                if (args.length >= 2 && args[0].equalsIgnoreCase("save")) {
                    String mapFolderName = args[1];
                    player.sendMessage("§aMap Saved!");
                    World world = Bukkit.getWorld(mapFolderName);
                    if (world != null) {
                        world.save();
                        saveMap(mapFolderName);
                    } else {
                        player.sendMessage("§cCan't find the: " + mapFolderName);
                    }
                    return true;
                }
                if (args.length >= 2 && args[0].equalsIgnoreCase("del")) {
                    teleportToLobby(player);
                    String mapFolderName = args[1];
                    delMap(mapFolderName);
                    return true;
                }
                if (args.length >= 1 && args[0].equalsIgnoreCase("help")) {
                    player.sendMessage(ChatColor.GREEN + "/mw help §f- 本指令");
                    player.sendMessage(ChatColor.GREEN + "/mw tp <世界資料夾名稱> §f- 傳送到該世界");
                    player.sendMessage(ChatColor.GREEN + "/mw import <世界資料夾名稱> §f- 導入該世界");
                    player.sendMessage(ChatColor.GREEN + "/mw save <世界資料夾名稱> §f- 儲存該世界");
                    player.sendMessage(ChatColor.GREEN + "/mw create <世界資料夾名稱> <世界類型> §f- 創建一個特殊類型的世界");
                    player.sendMessage(ChatColor.GREEN + "/mw del <世界資料夾名稱> §c- 刪除該世界");

                    return true;
                }
                if (args.length >= 3 && args[0].equalsIgnoreCase("create")) {
                    String mapFolderName = args[1];
                    String worldType = args[2].toLowerCase();

                    WorldCreator worldCreator = new WorldCreator(mapFolderName);

                    if (worldType.equals("void")) {
                        worldCreator.type(WorldType.FLAT);
                        worldCreator.generatorSettings("3;minecraft:air;1;");
                    } else if (worldType.equals("normal")) {
                        worldCreator.type(WorldType.NORMAL);
                    } else if (worldType.equals("superflat")) {
                        worldCreator.type(WorldType.FLAT);
                    } else {
                        player.sendMessage("§cInvalid world type. Use 'void', 'normal', or 'superflat'.");
                        return true;
                    }
                    World newWorld = Bukkit.createWorld(worldCreator);
                    saveMap(mapFolderName);

                    if (newWorld != null) {
                        player.sendMessage("§aCreated a new void world: " + mapFolderName);
                    } else {
                        player.sendMessage("§cFailed to create the void world: " + mapFolderName);
                    }
                    return true;
                }

                if (args.length >= 1 && args[0].equalsIgnoreCase("setlobby")) {
                    setLobby(player);
                    return true;
                }
            } else {
                player.sendMessage("§cYou don't have permission to do this.");
                return true;
            }

        }
        return false;
    }

    private void teleportToLobby(Player player) {
        File configFile = new File(Main.instance.getDataFolder(), "lobby.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        String worldName = config.getString("lobby.world");
        double x = config.getDouble("lobby.x");
        double y = config.getDouble("lobby.y");
        double z = config.getDouble("lobby.z");

        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            Location lobbyLocation = new Location(world, x, y, z);
            player.teleport(lobbyLocation);
        }
    }

    private void setLobby(Player player) {
        File configFile = new File(Main.getPlugin(Main.class).getDataFolder(), "lobby.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        config.set("lobby.world", player.getWorld().getName());
        config.set("lobby.x", player.getLocation().getX());
        config.set("lobby.y", player.getLocation().getY());
        config.set("lobby.z", player.getLocation().getZ());
        config.set("lobby.yaw", player.getLocation().getYaw()); //float
        config.set("lobby.pitch", player.getLocation().getPitch()); //float

        try {
            config.save(configFile);
            player.sendMessage("§aLobby configuration created.");
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage("§cError creating lobby configuration.");
        }
    }

}

---

# MultiWorld Plugin - Usage Guide

## Overview

The MultiWorld plugin allows server administrators to manage multiple worlds efficiently within a Bukkit/Spigot environment. With features like teleportation, world importing, saving, creating special types of worlds, and more, MultiWorld enhances the server's flexibility.

## Commands

### General Syntax:

```bash
/mw <subcommand> [args]
```

### Available Subcommands:

- **help**: Display help information for MultiWorld commands.

```bash
/mw help
```

- **tp**: Teleport to a specified world.

```bash
/mw tp <world_folder_name>
```

- **import**: Import an existing world into the server.

```bash
/mw import <world_folder_name>
```

- **save**: Save changes made to a world.

```bash
/mw save <world_folder_name>
```

- **del**: Delete a world and teleport to the server lobby.

```bash
/mw del <world_folder_name>
```

- **create**: Create a new world with a specified type.

```bash
/mw create <world_folder_name> <world_type>
```

Supported world types: `void`, `normal`, `superflat`.

- **setlobby**: Set the server lobby location.

```bash
/mw setlobby
```

## Examples

1. Teleport to a world:

```bash
/mw tp my_world
```

2. Import an existing world:

```bash
/mw import existing_world
```

3. Save changes to a world:

```bash
/mw save my_world
```

4. Create a new void world:

```bash
/mw create new_void_world void
```

5. Set the server lobby:

```bash
/mw setlobby
```

## Note

- Ensure you have the necessary permissions (`mw.*` or specific subcommand permissions) to use MultiWorld commands.
- Use `/mw help` for a quick in-game reference.

## API

### `loadMaps(String mapFolderName)`

This method is used to load maps from the specified `mapFolderName` into the server. It copies the content of the map folder to the server's destination folder, making the map available for use.

**Usage:**

```java
loadMaps("myMapFolder");
```

### `importMap(String mapFolderName)`

This method is a wrapper for `loadMaps()`. It is used to import maps into the server, making them accessible for various operations.

**Usage:**

```java
importMap("myMapFolder");
```

### `saveMap(String mapFolderName)`

This method is responsible for saving the changes made to a map. It copies the content of the specified `mapFolderName` to the server's `Maps` folder.

**Usage:**

```java
saveMap("myMapFolder");
```

### `delMap(String mapFolderName)`

This method deletes a map, including unloading it and teleporting all players in that world to the server lobby. It also updates the lobby location in the configuration file.

**Usage:**

```java
delMap("myMapFolder");
```

### `teleportToMap(Player player, String mapFolderName)`

This method teleports a player to the specified map. It loads the map if not already loaded and teleports the player to the map's spawn location.

**Usage:**

```java
Player player = // get the player object
teleportToMap(player, "myMapFolder");
```

### `unloadWorld(String worldName)`

This method unloads a world with the given `worldName` from the server. It's useful for freeing up server resources when a world is no longer needed.

**Usage:**

```java
unloadWorld("myWorld");
```

Feel free to use these examples in your code without referencing the `API` class directly. This makes the code cleaner and more concise. Adjust the method names and descriptions as needed for clarity.

---


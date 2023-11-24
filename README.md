
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

## Permissions

- `mw.*`: Grants access to all MultiWorld commands.
- `mw.tp`: Allows teleportation to worlds.
- `mw.import`: Allows importing worlds.
- `mw.save`: Allows saving worlds.
- `mw.del`: Allows deleting worlds.
- `mw.create`: Allows creating new worlds.
- `mw.setlobby`: Allows setting the server lobby.

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

---

Feel free to customize the guide based on additional features or specifics of your plugin. This guide is a starting point and can be expanded as needed.

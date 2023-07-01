# LPPronouns

A simple, cross API plugin that allows players to set their pronouns.

Works on: Spigot, BungeeCord, Fabric, Forge, and Velocity

Link to our support: [Discord](https://discord.gg/NffvJd95tk)

If you need the plugin ported to a specific API/version, please open an issue on GitHub.

## Download

- [GitHub](https://github.com/p0t4t0sandwich/LPPronouns/releases)
- [Spigot](https://www.spigotmc.org/resources/lppronouns.110206/)
- [Hangar](https://hangar.papermc.io/p0t4t0sandwich/LPPronouns)
- [Modrinth](https://modrinth.com/plugin/lppronouns)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/lppronouns)

### Compatibility Cheatsheet

| Server type | Versions    | Jar Name                               |
|-------------|-------------|----------------------------------------|
| All 1.19    | 1.19-1.19.4 | `LPPronouns-<version>-1.19.jar`        |
| All 1.20    | 1.20-1.20.x | `LPPronouns-<version>-1.20.jar`        |
| Bukkit      | 1.8-1.20.x  | `LPPronouns-<version>-bukkit.jar`      |
| BungeeCord  | 1.20-1.20.x | `LPPronouns-<version>-bungee.jar`      |
| Velocity    | API v3      | `LPPronouns-<version>-velocity.jar`    |
| Fabric 1.17 | 1.17-1.19.4 | `LPPronouns-<version>-fabric-1.17.jar` |
| Fabric 1.20 | 1.20-1.20.x | `LPPronouns-<version>-fabric-1.20.jar` |
| Forge 1.19  | 1.19-1.19.4 | `LPPronouns-<version>-forge-1.19.jar`  |
| Forge 1.20  | 1.20-1.20.x | `LPPronouns-<version>-forge-1.20.jar`  |

## Dependencies

This plugin requires [LuckPerms](https://luckperms.net/) to function.

## Commands and Permissions

| Command                                    | Permission            | Description                         |
|--------------------------------------------|-----------------------|-------------------------------------|
| `/pronouns`                                | `lppronouns.pronouns` | Allows you to manage your pronouns. |
| `/pronouns list`                           | `N/A`                 | Lists all available pronouns.       |
| `/pronouns clear/delete/none/remove/reset` | `N/A`                 | Clears your pronouns.               |
| `/pronouns <pronoun>`                      | `N/A`                 | Sets your pronouns.                 |

## Configuration

```yaml
# Database configuration
# Supported storage types: filesystem, mongodb, mysql
storage:
  # Filesystem configuration
  type: filesystem
  config:
    # The directory to store player data in
    directory: "playerdata"

# MongoDB configuration
#  type: mongodb
#  config:
#    host: localhost
#    port: 27017
#    database: playerdata
#    username: root
#    password: password
#    authSource: admin

# MySQL configuration
#  type: mysql
#  config:
#    host: localhost
#    port: 3306
#    database: playerdata
#    username: root
#    password: password

# Message formatting configuration
formatting:
  # This sets LPPronouns as the server's chat formatter
  enabled: true
  # %player% - Player name
  # %message% - Message
  # %server% - Server name
  # %prefix% - Player prefix
  # %suffix% - Player suffix
  # %displayname% - Player display name
  format: "<%displayname% (%suffix%)> %message%"

# Pronoun configuration
pronouns:
  none: ""
  
  any: any
  other: other
  ask: ask
  avoid: avoid
  # ...
```

## TODO

- Create a dynamic pronoun system (mix and match pronouns without needing to hardcode them in the config)
- Improve MySQL support
- add sqlite support
- add h2 support
- Fix weird Forge errors in log
- Add command permissions
  - Forge
  - Fabric
- Add tab completion? -- API dependent
- Add table prefix option in config
- Figure out why velocity can't run the command async
- Config option for suffix weight
- Config option to select weather to use a suffix or prefix
- Option to integrate DiscordSRV contexts

### Rebuild Ideas

- Create a way to "stack" the pronouns
  - Example: `They/Them` + `She/Her` = `They/She`
  - Example: `He/Him` + `They/Them` + `She/Her` = `He/They/She`

# Release Notes
- Added the filesystem as a viable storage method
- Actually registered the BukkitPlayerMessageListener event, so chat formatting should work now
- Fixed an issue where the old pronouns weren't removed from luckperms

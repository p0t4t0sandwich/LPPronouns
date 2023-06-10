# LPPronouns

A simple, cross API plugin that allows players to set their pronouns.

Works on: Spigot, BungeeCord, Forge, Fabric, and Velocity

Link to our support: [Discord](https://discord.gg/jec2jpdj7A)

## Download

- [GitHub](https://github.com/p0t4t0sandwich/LPPronouns/releases)
- [Spigot](https://www.spigotmc.org/resources/lppronouns.110206/)
- [Hangar](https://hangar.papermc.io/p0t4t0sandwich/LPPronouns)
- [Modrinth](https://modrinth.com/plugin/lppronouns)
- [CurseForge](https://legacy.curseforge.com/minecraft/mc-mods/lppronouns)

## Dependencies

This plugin requires [LuckPerms](https://luckperms.net/) to function.

## Commands

| Command     | Permission | Description         |
|-------------|------------|---------------------|
| `/pronouns` | `WIP`      | Sets your pronouns. |

## Configuration

```yaml
# Database configuration
# Supported storage types: mongodb, mysql
storage:
  type: mongodb
  config:
    host: localhost
    port: 27017
    database: playerdata
    username: root
    password: password

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

- [ ] Improve MySQL support
- [ ] add sqlite support
- [ ] add h2 support
- [ ] add filestorage support
- [ ] Fix weird Forge errors in log
- [ ] Add command permissions
- [ ] Add tab completion? -- API dependent
- [ ] Add table prefix option in config
- [ ] Include a proper check for LuckPerms, gracefully disable if not found
- [ ] Figure out why velocity can't run the command async
- [ ] Create a proper API
- [ ] Sponge support? API 7/8
- [ ] Nukkit support?

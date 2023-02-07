# Minecraft Armor Nerf

Tired of protection being too powerful? Fed up with the slap fights, waiting for the other player's four sets of gear to
degrade? Well, wait no more!

This mod does two main things:
1. Removes armor toughness. This is an opinionated change, so please [read up on what toughness does first!](https://minecraft.fandom.com/wiki/Armor#Armor_toughness)
2. Makes armor and protection configurable.

Grab it on [Modrinth](https://modrinth.com/mod/armor-nerf)

# Purpose

I'm running a server with a [custom factions mod pack](https://github.com/ryleu/factions-pack), and my players complained
about PvP being uninteresting. My solution was to entirely change the meta by making strategy more important than raw
skill.

# Configuration

All configuration is done through the file `config/armornerf.json` in your `.minecraft` directory:

```json
{
  "armorMultiplier":0.5,
  "protectionMultiplier":0.5
}
```

`armorMultiplier` is the amount of damage protection that full diamond armor (a full armor bar) will provide.

`protectionMultiplier` is multiplied by what the protection enchantment would normally provide in vanilla

# Questions

Feel free to reach out in the issues section of this page.

# Credit

 - [wizardwatch](https://github.com/wizardwatch) for the icon

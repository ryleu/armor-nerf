# Minecraft Armor Nerf

Tired of protection being too powerful? Fed up with the slap fights, waiting for the other player's four sets of gear to
degrade? Well, wait no more!

This mod does a few things:
1. Make the max damage reduction by armor points configurable
2. Make the damage reduction provided by protection configurable
3. Provide a few alternate ways of applying toughness
4. Remove armor caps. Total armor or protection point values above 20 will bypass the configured limits, similarly to
the mod [AttributeFix](https://modrinth.com/mod/attributefix)

Grab it on [Modrinth](https://modrinth.com/mod/armor-nerf)

# Purpose

I'm working on a total combat overhaul, and the main issue I discovered is that armor is simply too strong. My solution
is to simply nerf the everliving heck out of it.

# Configuration

All configuration is done through the file `config/armor-nerf.json` in your `.minecraft` directory or through
[Mod Menu](https://modrinth.com/mod/modmenu). Vanilla values are shown here:

```json
{
  "armorPercentage": 0.8,
  "protectionPerPoint": 0.04,
  "armorFormula": "VANILLA"
}
```

`armorMultiplier` is the amount of damage protection that full diamond armor (a full armor bar) will provide.

`protectionPerPoint` is the percentage of damage reduction granted by each protection point. You can read up on what a
protection point is [here](https://minecraft.wiki/w/Protection#Usage).

`armorFormula` is the formula used to determine how armor and toughness affect damage taken. It can be one of these
options:

- `vanilla`: This is how vanilla typically calculates it. Read up on how that works
[here](https://minecraft.wiki/w/Armor#Mechanics).
- `toughness_disabled`: Ignores the toughness system entirely.
- `flat_toughness`: Toughness reduces the incoming damage by a flat amount.
- `large_toughness`: Toughness makes armor more effective against larger attacks.

# Extending

If you want to add more formulae, you totally can! Just make your formula class extend `me.ryleu.armornerf.ArmorFormula`
and then register your new class with `me.ryleu.armornerf.ArmorNerf::registerFormula`. The `id` you pass in will become
the string you have to input into the config.

# Questions, Comments, or Concerns

Feel free to reach out in the issues section of this page, or on Discord `@ryleu`.

# Credit

 - [wizardwatch](https://github.com/wizardwatch) for the icon
 - [Rebel459](https://modrinth.com/user/Rebel459) for formula ideas

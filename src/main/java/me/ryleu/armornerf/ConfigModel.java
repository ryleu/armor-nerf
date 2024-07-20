package me.ryleu.armornerf;

import io.wispforest.owo.config.annotation.*;

import static me.ryleu.armornerf.ArmorNerf.MOD_ID;

@Modmenu(modId = MOD_ID)
@Config(name = MOD_ID, wrapperName = "ArmorNerfConfig")
public class ConfigModel {
    @RangeConstraint(min = 0F, max = 1F)
    public float armorPercentage = 0.4F;

    // at 0.0625, full prot 4 would provide 100% damage reduction. realistically no one should be setting it that high
    @RangeConstraint(min = 0F, max = 0.0625F)
    public float protectionPerPoint = 0.02F;

    @Hook
    @PredicateConstraint("armorFormulaPredicate")
    public String armorFormula = "toughness_disabled";

    public static boolean armorFormulaPredicate(String string) {
        return ArmorNerf.isRegistered(string);
    }
}

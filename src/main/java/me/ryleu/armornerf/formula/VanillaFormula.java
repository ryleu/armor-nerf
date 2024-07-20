package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import me.ryleu.armornerf.ArmorNerf;

public class VanillaFormula extends ArmorFormula {
    @Override
    public float calculate(float damage, float armor, float armorToughness) {
        float armorReduction = damage / (2F + armorToughness / 4F);
        float adjustedArmor = Math.min(Math.max(
                armor * 0.2F, // large damage amounts cannot lower the armor below 20% efficacy
                (armor - armorReduction)
        ) * ArmorNerf.CONFIG.armorPercentage() / 20F, 1F);
        return damage * (1F - adjustedArmor);
    }
}

package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import me.ryleu.armornerf.ArmorNerf;

/**
 * Toughness will protect more against larger damage values with this formula
 */
public class LargeToughnessFormula extends ArmorFormula {
    @Override
    public float calculate(float damage, float armor, float armorToughness) {
        float armorIncrease = Math.min(damage, 20F) * armorToughness / 100F;
        float adjustedArmor = ((armor + armorIncrease) / 20F) * ArmorNerf.CONFIG.armorPercentage();
        return (1 - adjustedArmor) * damage;
    }
}

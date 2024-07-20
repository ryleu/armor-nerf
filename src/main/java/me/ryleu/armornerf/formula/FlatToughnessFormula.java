package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import me.ryleu.armornerf.ArmorNerf;
import net.minecraft.util.math.MathHelper;

/**
 * Toughness will reduce damage by a flat amount with this formula
 */
public class FlatToughnessFormula extends ArmorFormula {
    @Override
    public float calculate(float damage, float armor, float armorToughness) {
        float adjustedDamage = damage - armorToughness / 4F; // divide by the number of armor pieces
        // divide by 20 to get a percentage of the armor bar filled
        float adjustedArmor = MathHelper.clamp((armor / 20F) * ArmorNerf.CONFIG.armorPercentage(), 0F, 1F);
        return adjustedDamage * (1F - adjustedArmor);
    }
}

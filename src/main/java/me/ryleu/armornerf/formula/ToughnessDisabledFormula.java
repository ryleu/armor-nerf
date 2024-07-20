package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import me.ryleu.armornerf.ArmorNerf;
import net.minecraft.util.math.MathHelper;

public class ToughnessDisabledFormula extends ArmorFormula {
    @Override
    public float calculate(float damage, float armor, float armorToughness) {
        float adjustedArmor = MathHelper.clamp((armor / 20F) * ArmorNerf.CONFIG.armorPercentage(), 0F, 1F);
        return damage * (1F - adjustedArmor);
    }
}

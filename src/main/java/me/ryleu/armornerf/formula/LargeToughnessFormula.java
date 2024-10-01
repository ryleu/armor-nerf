package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

/**
 * Toughness will protect more against larger damage values with this formula
 */
public class LargeToughnessFormula extends ArmorFormula {
    private final ArmorFormula baseFormula = new ToughnessDisabledFormula();

    @Override
    public float calculate(LivingEntity armorWearer, float damageAmount, DamageSource damageSource, float armor, float armorToughness) {
        float armorIncrease = Math.min(damageAmount, 20F) * armorToughness / 100F;

        return baseFormula.calculate(
                armorWearer,
                damageAmount,
                damageSource,
                armor + armorIncrease,
                armorToughness
        );
    }
}

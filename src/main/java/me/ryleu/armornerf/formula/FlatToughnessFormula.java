package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

/**
 * Toughness will reduce damage by a flat amount with this formula
 */
public class FlatToughnessFormula extends ArmorFormula {
    private final ArmorFormula baseFormula = new ToughnessDisabledFormula();

    @Override
    public float calculate(LivingEntity armorWearer, float damageAmount, DamageSource damageSource, float armor, float armorToughness) {
        float damageReduction = -armorToughness / 4F;

        return baseFormula.calculate(
                armorWearer,
                // reduce the incoming damage by the toughness
                Math.max(0F, damageAmount + damageReduction),
                damageSource,
                armor,
                armorToughness
        );
    }
}

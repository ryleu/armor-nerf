package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class VanillaFormula extends ArmorFormula {
    private final ArmorFormula baseFormula = new ToughnessDisabledFormula();

    @Override
    public float calculate(LivingEntity armorWearer, float damageAmount, DamageSource damageSource, float armor, float armorToughness) {
        float adjustedToughness = 2.0F + armorToughness / 4.0F;

        return baseFormula.calculate(
                armorWearer,
                damageAmount,
                damageSource,
                // calculate armor toughness effectiveness reduction
                Math.max(armor - damageAmount / adjustedToughness, armor * 0.2F),
                armorToughness
        );
    }
}

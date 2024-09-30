package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import me.ryleu.armornerf.ArmorNerf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class VanillaFormula extends ArmorFormula {
    @Override
    public float calculate(LivingEntity armorWearer, float damageAmount, DamageSource damageSource, float armor, float armorToughness) {
        float finalArmorFraction;

        float adjustedToughness = 2.0F + armorToughness / 4.0F;

        // calculate armor toughness effectiveness reduction
        float armorValue = Math.max(armor - damageAmount / adjustedToughness, armor * 0.2F);

        // whether to cap armor at a full bar -- will make many modded armors far more effective
        if (!ArmorNerf.CONFIG.removeArmorLimit()) {
            armorValue = Math.min(armorValue, 20.0F);
        }

        // apply the config numbers
        float adjustedArmorFraction = (armorValue / 20.0F) * ArmorNerf.CONFIG.armorPercentage();

        finalArmorFraction = applyEnchantments(damageSource, armorWearer, adjustedArmorFraction);

        return damageAmount * (1.0F - finalArmorFraction);
    }
}

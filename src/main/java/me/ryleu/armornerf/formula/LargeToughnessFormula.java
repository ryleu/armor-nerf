package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import me.ryleu.armornerf.ArmorNerf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

/**
 * Toughness will protect more against larger damage values with this formula
 */
public class LargeToughnessFormula extends ArmorFormula {
    @Override
    public float calculate(LivingEntity armorWearer, float damageAmount, DamageSource damageSource, float armor, float armorToughness) {
        float finalArmorFraction;

        // Increase armor at higher damage amounts
        float armorIncrease = Math.min(damageAmount, 20F) * armorToughness / 100F;
        float armorValue = armor + armorIncrease;

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

package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;

public class CrumblingArmorFormula extends ArmorFormula {
    private final ArmorFormula baseFormula = new ToughnessDisabledFormula();

    @Override
    public float calculate(
            LivingEntity armorWearer,
            float damageAmount,
            DamageSource damageSource,
            float armor,
            float armorToughness
    ) {
        float totalMaxDamage = 0;
        float totalDamage = 0;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.isArmorSlot()) {
                ItemStack itemStack = armorWearer.getEquippedStack(slot);

                int maxDamage = itemStack.getOrDefault(DataComponentTypes.MAX_DAMAGE, 0);
                if (maxDamage == 0) {
                    continue;
                }

                totalMaxDamage += maxDamage;
                totalDamage += itemStack.getDamage();
            }
        }

        float toughnessAdder = 0;
        if (totalMaxDamage > 0) {
            toughnessAdder = (armorToughness / 4F) * (1F - totalDamage / totalMaxDamage);
        }

        return baseFormula.calculate(
                armorWearer,
                damageAmount,
                damageSource,
                armor + toughnessAdder,
                armorToughness
        );
    }
}

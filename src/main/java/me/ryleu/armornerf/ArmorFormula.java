package me.ryleu.armornerf;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class ArmorFormula {
    public abstract float calculate(LivingEntity armorWearer, float damageAmount, DamageSource damageSource, float armor, float armorToughness);

    public static float applyEnchantments(DamageSource damageSource, LivingEntity armorWearer, float armorFraction) {
        ItemStack itemStack = damageSource.getWeaponStack();
        if (itemStack != null) {
            World world = armorWearer.getWorld();
            if (world instanceof ServerWorld serverWorld) {
                // apply enchantment effects like breaching
                return MathHelper.clamp(
                        EnchantmentHelper.getArmorEffectiveness(
                                serverWorld,
                                itemStack,
                                armorWearer,
                                damageSource,
                                armorFraction
                        ), 0.0F, 1.0F
                );
            }
        }

        return armorFraction;
    }
}

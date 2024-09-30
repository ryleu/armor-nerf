package me.ryleu.armornerf.mixin;

import me.ryleu.armornerf.ArmorNerf;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("unused")
@Mixin(DamageUtil.class)
public class DamageUtilMixin {
    @Inject(
            at = @At(value = "RETURN"),
            method = "getDamageLeft(Lnet/minecraft/entity/LivingEntity;FLnet/minecraft/entity/damage/DamageSource;FF)F",
            cancellable = true
    )
    private static void modifyGetDamageLeft(
            LivingEntity armorWearer, float damageAmount, DamageSource damageSource, float armor, float armorToughness, CallbackInfoReturnable<Float> cir
    ) {
        cir.setReturnValue(Math.max(0F, ArmorNerf.getToughnessFormula().calculate(armorWearer, damageAmount, damageSource, armor, armorToughness)));
    }

    @Inject(
            at = @At(value = "RETURN"),
            method = "getInflictedDamage(FF)F",
            cancellable = true
    )
    private static void modifyGetInflictedDamage(
            float damageDealt,
            float protection,
            CallbackInfoReturnable<Float> cir
    ) {
        float adjustedProtection = MathHelper.clamp(
                protection * ArmorNerf.CONFIG.protectionPerPoint(), 0F, 1F);
        cir.setReturnValue(damageDealt * (1F - adjustedProtection));
    }
}

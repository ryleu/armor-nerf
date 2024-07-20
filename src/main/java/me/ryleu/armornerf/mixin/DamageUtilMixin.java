package me.ryleu.armornerf.mixin;

import me.ryleu.armornerf.ArmorNerf;
import net.minecraft.entity.DamageUtil;
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
            method = "getDamageLeft(FFF)F",
            cancellable = true
    )
    private static void modifyGetDamageLeft(
            float damage,
            float armor,
            float armorToughness,
            CallbackInfoReturnable<Float> cir
    ) {
        cir.setReturnValue(Math.max(0F, ArmorNerf.getToughnessFormula().calculate(damage, armor, armorToughness)));
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

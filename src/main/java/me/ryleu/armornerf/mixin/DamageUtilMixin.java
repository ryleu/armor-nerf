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
        float f = MathHelper.clamp(armor * ArmorNerf.getArmorMultiplier(), 0F, 20F);
        cir.setReturnValue(damage * (1F - f / 20F));
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
        float f = MathHelper.clamp(protection * ArmorNerf.getProtectionMultiplier(), 0F, 20F);
        cir.setReturnValue(damageDealt * (1F - f / 20F));
    }
}

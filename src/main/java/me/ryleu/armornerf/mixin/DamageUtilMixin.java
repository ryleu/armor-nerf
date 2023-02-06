package me.ryleu.armornerf.mixin;

import net.minecraft.entity.DamageUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(DamageUtil.class)
public class DamageUtilMixin {
    // max armor effectiveness
    @Final
    @Shadow
    public static final float field_29962 = 10.0F;
}

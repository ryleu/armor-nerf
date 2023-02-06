package me.ryleu.armornerf.mixin;

import me.ryleu.armornerf.ArmorNerf;
import net.minecraft.entity.DamageUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.FileNotFoundException;

@SuppressWarnings("unused")
@Mixin(DamageUtil.class)
public class DamageUtilMixin {
    // max armor effectiveness
    @Final
    @Shadow
    public static final float field_29962 = getValue();

    private static float getValue() {
        ArmorNerf.readConfig();
        return (float) ArmorNerf.getMaxArmorMultiplier();
    }
}

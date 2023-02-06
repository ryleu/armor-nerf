package me.ryleu.armornerf.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
	@Inject(at = @At("RETURN"), method = "Lnet/minecraft/enchantment/EnchantmentHelper;getProtectionAmount(Ljava/lang/Iterable;Lnet/minecraft/entity/damage/DamageSource;)I", cancellable = true)
	private void getProtectionAmount(CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue((int) Math.ceil(cir.getReturnValue() / 2.0));
	}
}

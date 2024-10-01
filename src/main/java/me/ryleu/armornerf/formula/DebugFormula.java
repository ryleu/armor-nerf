package me.ryleu.armornerf.formula;

import me.ryleu.armornerf.ArmorFormula;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugFormula extends ArmorFormula {
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugFormula.class);

    private final FlatToughnessFormula flatToughnessFormula = new FlatToughnessFormula();
    private final LargeToughnessFormula largeToughnessFormula = new LargeToughnessFormula();
    private final ToughnessDisabledFormula toughnessDisabledFormula = new ToughnessDisabledFormula();
    private final CrumblingArmorFormula crumblingArmorFormula = new CrumblingArmorFormula();
    private final VanillaFormula vanillaFormula = new VanillaFormula();

    @Override
    public float calculate(LivingEntity armorWearer, float damageAmount, DamageSource damageSource, float armor, float armorToughness) {
        Text name = armorWearer.getCustomName();
        LOGGER.debug(
                "!![{},{},{},{},{},{},{}]",
                name,
                damageAmount,
                flatToughnessFormula.calculate(armorWearer, damageAmount, damageSource, armor, armorToughness),
                largeToughnessFormula.calculate(armorWearer, damageAmount, damageSource, armor, armorToughness),
                toughnessDisabledFormula.calculate(armorWearer, damageAmount, damageSource, armor, armorToughness),
                crumblingArmorFormula.calculate(armorWearer, damageAmount, damageSource, armor, armorToughness),
                vanillaFormula.calculate(armorWearer, damageAmount, damageSource, armor, armorToughness)
        );

        return 0;
    }

    public DebugFormula() {
        LOGGER.debug("!![name,none,flat,large,disabled,crumbling,vanilla]");
    }
}

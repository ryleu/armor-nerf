package me.ryleu.armornerf;

import me.ryleu.armornerf.formula.FlatToughnessFormula;
import me.ryleu.armornerf.formula.LargeToughnessFormula;
import me.ryleu.armornerf.formula.ToughnessDisabledFormula;
import me.ryleu.armornerf.formula.VanillaFormula;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class ArmorNerf implements ModInitializer {
	public static final String MOD_ID = "armor-nerf";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final me.ryleu.armornerf.ArmorNerfConfig CONFIG = me.ryleu.armornerf.ArmorNerfConfig.createAndLoad();

	static ArmorFormula armorFormula = new VanillaFormula();
	static HashMap<String, ArmorFormula> formulaRegistry = new HashMap<>();

	public static ArmorFormula getToughnessFormula() {
		return armorFormula;
	}

	/**
	 * Check if a formula is registered. Will always return true if the registry has not been created yet.
	 * @param id ID of the formula
	 * @return Whether it is registered
	 */
	public static boolean isRegistered(String id) {
		return formulaRegistry == null || formulaRegistry.containsKey(id);
	}

	/**
	 * Register a new formula. Will fail if the registry has not been created yet.
	 * @param id ID of the new formula to register.
	 * @param formula Class of the new formula to register.
	 */
	public static void registerFormula(String id, ArmorFormula formula) {
		formulaRegistry.put(id, formula);
	}

	@Override
	public void onInitialize() {
		registerFormula("vanilla", new VanillaFormula());
		registerFormula("toughness_disabled", new ToughnessDisabledFormula());
		registerFormula("flat_toughness", new FlatToughnessFormula());
		registerFormula("large_toughness", new LargeToughnessFormula());

		CONFIG.subscribeToArmorFormula((String newFormulaId) -> {
			ArmorFormula newFormula = formulaRegistry.get(newFormulaId);
			if (newFormula == null) {
				CONFIG.armorFormula("toughness_disabled");
			} else {
				armorFormula = newFormula;
			}
		});
		LOGGER.info("Armor and protection nerfed.");
	}
}

package me.ryleu.armornerf;

import me.ryleu.armornerf.formula.*;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class ArmorNerf implements ModInitializer {
	public static final String MOD_ID = "armor-nerf";
	public static final me.ryleu.armornerf.ArmorNerfConfig CONFIG = me.ryleu.armornerf.ArmorNerfConfig.createAndLoad();

	private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static ArmorFormula armorFormula = new VanillaFormula();
	private static HashMap<String, ArmorFormula> formulaRegistry;

	public static ArmorFormula getArmorFormula() {
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
		formulaRegistry = new HashMap<>();

		registerFormula("vanilla", new VanillaFormula());
		registerFormula("toughness_disabled", new ToughnessDisabledFormula());
		registerFormula("flat_toughness", new FlatToughnessFormula());
		registerFormula("large_toughness", new LargeToughnessFormula());
		registerFormula("crumbling_armor", new CrumblingArmorFormula());
		registerFormula("debug", new DebugFormula());

		setArmorFormula(CONFIG.armorFormula());

		CONFIG.subscribeToArmorFormula(this::setArmorFormula);
	}

	private void setArmorFormula(String newFormulaId) {
		ArmorFormula newFormula = formulaRegistry.get(newFormulaId);
		if (newFormula == null) {
			CONFIG.armorFormula("toughness_disabled");
		} else {
			LOGGER.info("Set formula to {}", newFormulaId);
			armorFormula = newFormula;
		}
	}
}

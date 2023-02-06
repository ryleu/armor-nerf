package me.ryleu.armornerf;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmorNerf implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("armor-nerf");

	@Override
	public void onInitialize() {
		LOGGER.info("Armor and protection nerfed by 50%.");
	}
}

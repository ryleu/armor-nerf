package me.ryleu.armornerf;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.DamageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ArmorNerf implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("armor-nerf");
	private static final File configFile = new File("config/armornerf.json");

	private static final Gson gson = new Gson();
	private static Map<String, Double> configMap = new LinkedHashMap<>();
	private static final TypeToken<Map<String, Double>> configMapType = new TypeToken<>() {};

	private static final double defaultMaxArmorMultiplier = .4;
	private static final double defaultProtectionAmountMultiplier = .5;

	@Override
	public void onInitialize() {
		LOGGER.info("Armor and protection nerfed by 50%.");
		readConfig();
	}

	public static double getMaxArmorMultiplier() {
		return configMap.getOrDefault("maxArmorMultiplier", defaultMaxArmorMultiplier) * DamageUtil.field_29963;
	}

	public static double protectionAmountMultiplier() {
		return configMap.getOrDefault("protectionAmountMultiplier", defaultProtectionAmountMultiplier);
	}

	public static void writeConfig(double maxArmorMultiplier, double protectionAmountMultiplier) throws IOException {
		configMap.put("maxArmorMultiplier", maxArmorMultiplier);
		configMap.put("protectionAmountMultiplier", protectionAmountMultiplier);

		FileWriter writer = new FileWriter(configFile.getPath());
		writer.write(gson.toJson(configMap));
		writer.close();
	}

	public static void readConfig() {
		try {
			if (configFile.createNewFile()) {
				writeConfig(defaultMaxArmorMultiplier, defaultProtectionAmountMultiplier);
			} else {
				Scanner scanner = new Scanner(configFile);

				StringBuilder data = new StringBuilder();
				while (scanner.hasNextLine()) {
					data.append(scanner.nextLine());
				}

				configMap = gson.fromJson(data.toString(), configMapType);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		LOGGER.info(gson.toJson(configMap));
	}
}

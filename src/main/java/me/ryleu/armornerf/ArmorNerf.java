package me.ryleu.armornerf;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ArmorNerf implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("armor-nerf");
	private static final File configFile = new File("config/armornerf.json");

	private static final Gson gson = new Gson();
	private static Map<String, Float> configMap = new LinkedHashMap<>();
	private static final TypeToken<Map<String, Float>> configMapType = new TypeToken<>() {};

	private static final float defaultArmorMultiplier = .5F;
	private static final float defaultProtectionMultiplier = .5F;

	@Override
	public void onInitialize() {
		LOGGER.info("Armor and protection nerfed.");
		readConfig();
	}

	public static float getArmorMultiplier() {
		return configMap.getOrDefault("armorMultiplier", defaultArmorMultiplier);
	}

	public static float getProtectionMultiplier() {
		return configMap.getOrDefault("protectionMultiplier", defaultProtectionMultiplier);
	}

	private static void writeConfig() throws IOException {
		writeConfig(
				getArmorMultiplier(),
				getProtectionMultiplier()
		);
	}

	public static void writeConfig(float armorMultiplier, float protectionMultiplier) throws IOException {
		configMap.put("armorMultiplier", armorMultiplier);
		configMap.put("protectionMultiplier", protectionMultiplier);

		FileWriter writer = new FileWriter(configFile.getPath());
		writer.write(gson.toJson(configMap));
		writer.close();
	}

	public static void readConfig() {
		try {
			if (configFile.createNewFile()) {
				writeConfig();
			}

			Scanner scanner = new Scanner(configFile);
			StringBuilder data = new StringBuilder();
			while (scanner.hasNextLine()) {
				data.append(scanner.nextLine());
			}

			Map<String, Float> newMap = gson.fromJson(data.toString(), configMapType);


			boolean mustWrite = false;

			// data validation
			for (String key : new String[]{"armorMultiplier", "protectionMultiplier"}) {
				mustWrite = !inBounds(newMap.getOrDefault(key, -1F)) || mustWrite;
			}

			configMap = newMap;

			if (mustWrite) writeConfig();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		LOGGER.info(gson.toJson(configMap));
	}

	private static boolean inBounds(float number) {
		return 0F <= number && number <= 1F;
	}
}

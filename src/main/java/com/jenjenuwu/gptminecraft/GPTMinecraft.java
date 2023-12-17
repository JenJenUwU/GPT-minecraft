package com.jenjenuwu.gptminecraft;

import com.jenjenuwu.gptminecraft.util.ModRegistries;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GPTMinecraft implements ModInitializer {
    public static final String MOD_ID = "gpt-minecraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModRegistries.register();
        LOGGER.info("Hello Fabric world!");
    }
}
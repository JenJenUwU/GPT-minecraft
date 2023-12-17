package com.jenjenuwu.gptminecraft.util;

import com.jenjenuwu.gptminecraft.command.AccessOpenAI;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModRegistries {
    public static void register() {
        registerCommands();
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> {
            AccessOpenAI.register(dispatcher);
        });
    }
}

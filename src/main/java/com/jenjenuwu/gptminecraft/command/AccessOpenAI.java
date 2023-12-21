package com.jenjenuwu.gptminecraft.command;

import com.jenjenuwu.gptminecraft.GPTMinecraft;
import com.jenjenuwu.gptminecraft.openAI.OpenAiModel;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.List;

public class AccessOpenAI {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("gpt")
                .then(CommandManager.literal("minecraft")
                        .then(CommandManager.argument("input", StringArgumentType.greedyString())
                                .executes(context -> runMinecraftCommand(context.getSource(), context.getArgument("input", String.class)))
                        )
                )
                .then(CommandManager.argument("input", StringArgumentType.greedyString())
                        .executes(context -> run(context.getSource(), context.getArgument("input", String.class)))
                )
        );

    }

    public static int run(ServerCommandSource source, String input) {
        GPTMinecraft.LOGGER.info("Processing command: " + input);
        source.sendFeedback(() -> Text.of("Processing..."), false);
        String response = OpenAiModel.getResponse(input, "");
        GPTMinecraft.LOGGER.info("Command processed: " + response);
        source.sendFeedback(() -> Text.of("Response: " + response), false);
        return 1;
    }

    public static int runMinecraftCommand(ServerCommandSource source, String input) {
        GPTMinecraft.LOGGER.info("Processing Minecraft command: " + input);
        source.sendFeedback(() -> Text.of("Processing..."), false);
        String response = OpenAiModel.getResponse(input, "You are a system that built for translating player's requirements into Minecraft commands. " +
                "Your only task it to provide translated commands with line breaks. " +
                "Don't teach the player to do anything and don't ask or explain anything. " +
                "The commands should be executable without any modification. ");
        GPTMinecraft.LOGGER.info("Minecraft command processed: " + response);
        List<String> commands = List.of(response.split("\n")).stream().filter(s -> !s.isBlank()).toList();
        commands.forEach(command -> {
            GPTMinecraft.LOGGER.info("Executing command: " + command);
            source.sendFeedback(() -> Text.of("Executing command: " + command), false);
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            CommandManager commandManager = minecraftClient.getServer().getCommandManager();
            commandManager.executeWithPrefix(source, command);
        });
        return 1;
    }
}
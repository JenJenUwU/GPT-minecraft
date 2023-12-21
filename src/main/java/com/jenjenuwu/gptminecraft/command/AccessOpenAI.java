package com.jenjenuwu.gptminecraft.command;

import com.jenjenuwu.gptminecraft.openAI.OpenAiModel;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class AccessOpenAI {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("gpt")
                //.requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("input", StringArgumentType.greedyString())
                        .executes(context -> run(context.getSource(), context.getArgument("input", String.class)))
                )
        );

    }

    public static int run(ServerCommandSource source, String input) {
        source.sendFeedback(() -> Text.of("Response: " + OpenAiModel.getResponse(input, "You are a system that built for translating player's requirements into Minecraft commands. " +
                "If the user asks something relevant to the game Minecraft" +
                "Your only task it to provide translated commands with line breaks. " +
                "Don't teach the player to do anything and don't ask or explain anything. " +
                "The commands should be executable without any modification. " +
                "If the user asks something irrelevant to Minecraft" +
                "Answer it normally")), false);
        return 1;
    }
}

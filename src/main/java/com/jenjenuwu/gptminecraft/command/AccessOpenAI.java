package com.jenjenuwu.gptminecraft.command;

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
        source.sendFeedback(() -> Text.of("You entered: " + input), false);
        return 1;
    }
}
